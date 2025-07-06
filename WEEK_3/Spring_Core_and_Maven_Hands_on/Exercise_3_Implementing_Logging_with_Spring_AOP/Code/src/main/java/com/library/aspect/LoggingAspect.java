package com.library.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class LoggingAspect {

    @Around("execution(* com.library..*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object result = joinPoint.proceed();  // Proceed with the original method

        long end = System.currentTimeMillis();
        System.out.println("Execution Time of " + joinPoint.getSignature().getName() + ": " + (end - start) + " ms");
        return result;
    }
}
