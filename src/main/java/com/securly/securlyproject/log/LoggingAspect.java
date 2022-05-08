package com.securly.securlyproject.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.validation.support.BindingAwareModelMap;

import java.lang.reflect.Method;
import java.util.Collection;

@Slf4j
@Component
@Aspect
public class LoggingAspect {

    @Pointcut("@annotation(EnableLog)")
    public void executeLogging() {
    }

    @Around(value = "executeLogging()")
    public Object logMethodCall(ProceedingJoinPoint joinPoint) throws Throwable {

        long startTime = System.currentTimeMillis();
        Object returnValue = joinPoint.proceed();

        long totalTime = System.currentTimeMillis() - startTime;

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        EnableLog myAnnotation = method.getAnnotation(EnableLog.class);
        String logPattern = myAnnotation.value();
        String methodName = signature.getName();

        StringBuilder message = new StringBuilder("execute method")
            .append(" '").append(methodName).append("'");

        Object[] args = joinPoint.getArgs();
        if (null != args && args.length > 0) {
            message.append(" with parameters: {");
            for (Object arg : args) {
                if (!(arg instanceof BindingAwareModelMap)) {
                    logPattern = logPattern.replaceFirst("%s", String.valueOf(arg));
                }
            }
            message.append(logPattern).append("}");
        }

        if (returnValue != null) {
            if (returnValue instanceof Collection) {
                message.append(", return-value: ").append(((Collection) returnValue).size()).append(" instance(s)");
            } else {
                message.append(", return-value: ").append("'").append(returnValue).append("'");
            }
        }

        message.append(" during: ").append("'").append(totalTime).append("ms").append("'");

        log.info(message.toString());
        return returnValue;
    }
}