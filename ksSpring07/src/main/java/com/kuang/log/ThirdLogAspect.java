package com.kuang.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class ThirdLogAspect {

    @Before("execution(* com.kuang.impl.ThirdUserServiceImpl.*(..))")
    public void bf() {
        System.out.println("-------------方法执行前--------------");
    }

    @After("execution(* com.kuang.impl.ThirdUserServiceImpl.*(..))")
    public void af() {
        System.out.println("-----------------方法执行后---------------------");
    }

    @Around("execution(* com.kuang.impl.ThirdUserServiceImpl.*(..))")
    public void ar(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("环绕前");
        System.out.println("getSignature = " + joinPoint.getSignature());
        Object proceed = joinPoint.proceed();
        System.out.println("环绕后");
        System.out.println(proceed);
    }
}
