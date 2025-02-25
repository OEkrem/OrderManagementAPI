package com.oekrem.SpringMVCBackEnd.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Component
public class MethodExecutionLoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.oekrem.SpringMVCBackEnd.services..*(..))")
    public void serviceMethods(){}

    @Pointcut("execution(* com.oekrem.SpringMVCBackEnd.repository..*(..))")
    public void repositoryMethods(){}

    @Around("serviceMethods() || repositoryMethods()")
    public Object aroundService(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        long startTime = System.currentTimeMillis();
        logger.info("[START] Method: {} | With args: {}", methodName, args);

        Object result = joinPoint.proceed();
        long duration = System.currentTimeMillis() - startTime;

        logger.info("[END] Method: {} | Duration: {} | With result: {}", methodName, duration, result);
        return result;
    }

}
