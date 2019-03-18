package com.epam.travelagency.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.LoggerFactory;

@Aspect
public class Logger {

    private org.slf4j.Logger LOG = LoggerFactory.getLogger(Logger.class);

    @After("within(com.epam.travelagency.service.*)")
    public void log(JoinPoint joinPoint){
        Class clazz = joinPoint.getTarget().getClass();
        String methodName = joinPoint.getSignature().getName();
        LOG.info(String.format("Method '%s' invocation in %s class", methodName, clazz.getName()));
    }

    @Before("@annotation(org.springframework.transaction.annotation.Transactional)")
    public void logTransactional(JoinPoint joinPoint){
        Class clazz = joinPoint.getTarget().getClass();
        String methodName = joinPoint.getSignature().getName();
        LOG.info(String.format("Transactional method '%s' in %s begins...", methodName, clazz.getName()));
    }

    @After("@annotation(org.springframework.transaction.annotation.Transactional)")
    public void logTransactionalAfter(JoinPoint joinPoint){
        Class clazz = joinPoint.getTarget().getClass();
        String methodName = joinPoint.getSignature().getName();
        LOG.info(String.format("Transaction in method '%s' in %s committed...", methodName, clazz.getName()));
    }
}
