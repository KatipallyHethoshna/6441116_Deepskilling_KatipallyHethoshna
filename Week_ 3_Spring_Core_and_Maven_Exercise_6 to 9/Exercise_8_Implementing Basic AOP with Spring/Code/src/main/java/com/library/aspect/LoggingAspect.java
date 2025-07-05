package com.library.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class LoggingAspect {

    @Before("execution(* com.library.service.BookService.*(..))")
    public void logBefore() {
        System.out.println("[AOP] Before method: Logging started");
    }

    @After("execution(* com.library.service.BookService.*(..))")
    public void logAfter() {
        System.out.println("[AOP] After method: Logging ended");
    }
}
