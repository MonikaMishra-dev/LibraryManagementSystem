package com.projects.LibraryManagementSystem.aspects;

import com.projects.LibraryManagementSystem.dto.requestdtos.TxnRequest;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceParamPrinter {
    private static final Logger log = LoggerFactory.getLogger(ServiceParamPrinter.class);

    @Pointcut("execution(* com.projects.LibraryManagementSystem.service.impl.TxnService.createTxn(com.projects.LibraryManagementSystem.dto.requestdtos.TxnRequest))")
    public void createServiceMethod(){}

    @Before(value = "createServiceMethod() && args(txnRequest)")
    public void txnServiceAdvice(TxnRequest txnRequest){
        log.info("Arguments : {}",txnRequest);
    }

}
