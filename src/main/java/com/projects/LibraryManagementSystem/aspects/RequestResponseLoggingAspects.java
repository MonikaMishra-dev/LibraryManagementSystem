package com.projects.LibraryManagementSystem.aspects;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class RequestResponseLoggingAspects {

    private static final Logger log = LoggerFactory.getLogger(RequestResponseLoggingAspects.class);

    @Pointcut("execution(* com.projects.LibraryManagementSystem.controller..*(..))")
    public void controllerMethods() {
    }

    @Before("controllerMethods()")
    public void loggingRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        log.info("Request: {} {}", request.getMethod(), request.getRequestURI());
        log.info("Request Headers: ");
        request.getHeaderNames().
                asIterator().
                forEachRemaining(header -> log.info("header:{}", request.getHeader(header)));
    }

    @AfterReturning(value = "controllerMethods()",returning = "response")
    public void loggingResponse(Object response){
        if(response instanceof HttpServletResponse){
            HttpServletResponse response1 = (HttpServletResponse) response;
            log.info("response status : {}", response1.getStatus());
        }
        else
            log.info("response: {}", response.toString());
    }
}
