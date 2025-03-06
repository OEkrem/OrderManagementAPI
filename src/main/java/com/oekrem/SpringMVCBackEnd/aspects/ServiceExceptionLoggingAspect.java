package com.oekrem.SpringMVCBackEnd.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceExceptionLoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.oekrem.SpringMVCBackEnd.services..*(..))")
    public void serviceMethods(){}

    @Pointcut("execution(* com.oekrem.SpringMVCBackEnd.repository..*(..))")
    public void repositoryMethods(){}

    @AfterThrowing(pointcut = "serviceMethods() || repositoryMethods()", throwing = "ex")
    public void logErrors(JoinPoint joinPoint, Exception ex){
        String methodName = joinPoint.getSignature().getName();
        logger.error("[ERROR] Method: {} | Exception: {}", methodName, ex.getMessage(), ex);
    }

}
