package com.mtcom.myboard.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {
	private final Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Pointcut(value="execution( * com.mtcom.myboard..*(..) )")
	private void logPointcut() {}
	
	@Before("logPointcut()")
	public void beforeLog(JoinPoint joinPoint) {
		Signature signature=joinPoint.getSignature();
		logger.debug("[Logger : befoer] 메소드 이름: "+signature.getName());
	}
	
	@After("logPointcut()")
	public void afterLog(JoinPoint joinPoint) {
		Signature signature=joinPoint.getSignature();
		logger.debug("[Logger : after] 메소드 이름: "+signature.getName());
	}
}
