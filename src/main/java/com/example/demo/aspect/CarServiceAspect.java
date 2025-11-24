package com.example.demo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CarServiceAspect {
    

    @Before("execution(* com.example.demo.service.CarService.*(..))")
    public void logBefore(){
        System.out.println("[Before] Метод CarService был вызван");
    }

    @Around("execution(* com.example.demo.service.CarService.*(..))")
    public Object logArround(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("[Around] Вход в метод" + joinPoint.getSignature());
        Object result = joinPoint.proceed();
        System.out.println("[Around] Выход в метода" + joinPoint.getSignature());
        return result;
    }
}
