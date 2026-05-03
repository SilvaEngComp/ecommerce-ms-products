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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import jakarta.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class LoggerApect {
    private final ThreadLocal<Integer> sequenceCounter = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };
    @Pointcut("execution(* com.eliabe.ecommerce.products.domain.service.*.*(..))")
    public void serviceMethods() {
    }

    @Pointcut("execution(* com.eliabe.ecommerce.products.web.controller.*.*(..))")
    public void controllerMethods() {
    }

    @Pointcut("execution(* com.eliabe.ecommerce.products.infrastructore.adapter.persistence.repository.*.*(..))")
    public void repositoryMethods() {
    }

    @Before("controllerMethods()")
    public void logControllerEntry(JoinPoint joinPoint) {
        int sequence = sequenceCounter.get() + 1;
        sequenceCounter.set(sequence);
        Object[] args = joinPoint.getArgs();
        String parameters = formatParameters(args);
        String endpoint = getEndpointInfo();
        log.info("----------------------{}----------------------", endpoint);
        log.info("[{}] | Entering: {}.{} | Parameters: {}",
                sequence,
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                parameters);
    }

    @Before("serviceMethods() || repositoryMethods()")
    public void logMethodEntry(JoinPoint joinPoint) {
        int sequence = sequenceCounter.get() + 1;
        sequenceCounter.set(sequence);
        Object[] args = joinPoint.getArgs();
        String parameters = formatParameters(args);
        log.info("[{}] >>> Entering: {}.{} | Parameters: {}",
                sequence,
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                parameters);
    }

    @After("serviceMethods() || controllerMethods() || repositoryMethods()")
    public void logMethodExit(JoinPoint joinPoint) {
        int sequence = sequenceCounter.get();
        log.info("[{}] <<< Exiting: {}.{}",
                sequence,
                joinPoint.getSignature().getName());
    }

    @Around("serviceMethods() || controllerMethods() || repositoryMethods()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object returnValue = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        int sequence = sequenceCounter.get();
        log.info("[{}] ⏱️ {}.{} executed in {} ms | Return: {}",
                sequence,
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
        int sequence = sequenceCounter.get();
        log.error("[{}] Exception in {}.{}: {}", sequence, joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), ex.getMessage());
    }

    private String getEndpointInfo() {
        try {
            ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attrs != null) {
                HttpServletRequest request = attrs.getRequest();
                return request.getMethod() + " " + request.getRequestURI();
            }
        } catch (Exception e) {
            // Ignore exceptions when request context is not available
        }
        return "N/A";
    }
}