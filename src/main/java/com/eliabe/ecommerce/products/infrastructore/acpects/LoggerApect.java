package com.eliabe.ecommerce.products.infrastructore.acpects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LoggerApect {
    @Pointcut("execution(* com.eliabe.ecommerce.products.domain.service.*.*(..))")
    public void serviceMethods() {
    }

    @Pointcut("execution(* com.eliabe.ecommerce.products.web.controller.*.*(..))")
    public void controllerMethods() {
    }

    @Pointcut("execution(* com.eliabe.ecommerce.products.infrastructore.adapter.persistence.repository.*.*(..))")
    public void repositoryMethods() {
    }

    @Before("serviceMethods() || controllerMethods() || repositoryMethods()")
    public void logMethodEntry(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String parameters = formatParameters(args);
        log.info(">>> Entering: {}.{} | Parameters: {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                parameters);
    }

    @After("serviceMethods() || controllerMethods() || repositoryMethods()")
    public void logMethodExit(JoinPoint joinPoint) {
        log.info("<<< Exiting: {}.{}",
                joinPoint.getSignature().getName());
    }

    @Around("serviceMethods() || controllerMethods() || repositoryMethods()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object returnValue = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        log.info("⏱️ {}.{} executed in {} ms | Return: {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                executionTime,
                formatReturnValue(returnValue));
        return returnValue;
    }

    private String formatParameters(Object[] args) {
        if (args == null || args.length == 0) {
            return "none";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            sb.append(args[i]);
            if (i < args.length - 1) {
                sb.append(", ");
            }
        }

        return sb.toString();
    }

    private String formatReturnValue(Object returnValue) {
        if (returnValue == null) {
            return "null";
        }
        if (returnValue instanceof String) {
            return "'" + returnValue + "'";
        }
        return returnValue.toString();
    }

    @AfterThrowing(pointcut = "serviceMethods() || controllerMethods() || repositoryMethods()", throwing = "ex")
    public void logExceptions(JoinPoint joinPoint, Throwable ex) {
        log.error("Exception in {}.{}: {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), ex.getMessage());
    }
}