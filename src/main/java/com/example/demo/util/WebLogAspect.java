package com.example.demo.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import org.jboss.logging.Logger;

import java.lang.reflect.Method;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Aspect
@Component
public class WebLogAspect {

    private Logger logger = Logger.getLogger(getClass());

    ThreadLocal<Long> startTime = new ThreadLocal<>();



    @Pointcut("execution(public * com.example.demo.controller..*.*(..))")
    public void  webLog(){}


    @Before("webLog()")

    public void doBefore(JoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());
        logger.info(startTime.get());
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        logger.info("RESPONSE : " + ret);
        logger.info("SPEND TIME : " + (System.currentTimeMillis() - startTime.get()));
    }

    @After("webLog()")
    public void afterAdvice(){
        System.out.println("Student profile has been setup."+"初始日志LOGEEER");
        logger.info("RESPONSE : "+"------------------");
        logger.info("SPEND TIME : " + (System.currentTimeMillis() - startTime.get()));
    }

    @AfterThrowing(pointcut = "webLog()", throwing = "ex")
    public void AfterThrowingAdvice(IllegalArgumentException ex){
        System.out.println("There has been an exception: " + ex.toString());
        logger.info("RESPONSE : " + ex);
        logger.info("SPEND TIME : " + (System.currentTimeMillis() - startTime.get()));
    }
    @Around("webLog()")
    public Object around(ProceedingJoinPoint point) throws Throwable{
        // 开始时间
        LocalDateTime beginTime = LocalDateTime.now();
        // 执行方法
        Object result = point.proceed();
        // 结束时间
        LocalDateTime endTime = LocalDateTime.now();
        Duration duration = Duration.between(beginTime, endTime);
        // 操作时长
        long seconds = duration.getSeconds();
        // 保存日志
        logger.info("RESPONSE : " + beginTime);
        logger.info("RESPONSE : " + endTime);
        logger.info("RESPONSE : " + result);
        logger.info("RESPONSE : " + seconds);
        return result;
    }

}
