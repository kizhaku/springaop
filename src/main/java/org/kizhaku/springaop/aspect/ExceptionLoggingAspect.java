package org.kizhaku.springaop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class ExceptionLoggingAspect {
    //@Autowired
    //Environment env;

    @Value("${appSecret}")
    private String appSecret;

    Logger logger = LoggerFactory.getLogger(ExceptionLoggingAspect.class);

//    @Around("execution(* org.kizhaku.springaop.controller..*(..))")
//    public Object handleExceptions(ProceedingJoinPoint joinPoint) throws Throwable {
//        try {
//            return joinPoint.proceed();
//        } catch (UserNotFoundException userNotFoundException) {
//            return ResponseEntity
//                    .status(HttpStatus.NOT_FOUND)
//                    .body(new ErrorResponse("Missing user", userNotFoundException.getMessage()));
//        } catch (Throwable e) {
//            return ResponseEntity
//                    .status(HttpStatus.NOT_FOUND)
//                    .body(new ErrorResponse("Server error", "Something went wrong"));
//        }
//    }

    @AfterThrowing(pointcut = "execution(* org.kizhaku.springaop..*(..))", throwing = "ex")
    public void logExceptions(JoinPoint joinPoint, Throwable ex) {
        logger.error("Exception in {} with args {} with Dsec {}: {}",
                joinPoint.getSignature(),
                Arrays.toString(joinPoint.getArgs()),
                //env.getProperty("appSecret"),
                appSecret,
                ex.getMessage(), ex);
    }
}
