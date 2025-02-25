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
public class ControllerExceptionLoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.oekrem.SpringMVCBackEnd.controller.*(..))")
    public void exceptionHandlerMethod(){}

    @AfterThrowing(pointcut = "exceptionHandlerMethod()", throwing = "ex")
    public void logControllerException(JoinPoint joinPoint, Throwable ex){
        String methodName = joinPoint.getSignature().getName();
        logger.error("[CONTROLLER ERROR] Method: {} | Exception: {}", methodName, ex.getMessage(), ex);
    }

}
