package com.global.book.aspect;


import org.aopalliance.intercept.Joinpoint;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Aspect

//the object with higher priority will be executed first
@Order(0)

@Component
public class MeasurementAspect {

	Logger log = LoggerFactory.getLogger(MeasurementAspect.class);
	
	//will be executed before and after each method of any class in service package -> (..) : any parameters
	@Around("execution(* com.global.book.service..*(..))")
	public Object logTime(ProceedingJoinPoint joinPoint) throws Throwable {
		long startTime = System.currentTimeMillis();
		StringBuilder sb = new StringBuilder("KPI");
		sb.append("[").append(joinPoint.getKind()).append("]\tfor: ").append(joinPoint.getSignature())
			.append("\twithArgs: ").append("(").append(StringUtils.join(joinPoint.getArgs(),",")).append(")");
		sb.append("\ttook: ");
		Object returnValue = joinPoint.proceed();
		log.info(sb.append(System.currentTimeMillis() - startTime).append(" ms.").toString());
		return returnValue;
		
	}	
	
	
}
