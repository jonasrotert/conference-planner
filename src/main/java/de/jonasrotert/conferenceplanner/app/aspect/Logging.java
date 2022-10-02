package de.jonasrotert.conferenceplanner.app.aspect;

import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Logging {

	@Pointcut("execution(* de.jonasrotert.conferenceplanner.app.controller.*.*(..))")
	public void controllerMethods() {
	}

	@After("controllerMethods()")
	public void logAfterControllerMethod(JoinPoint jp) {
		LogFactory.getLog(jp.getTarget().getClass()).info("After " + jp.getSignature().getName());
	}

	@Before("controllerMethods()")
	public void logRepositoryMethod(JoinPoint jp) {
		LogFactory.getLog(jp.getTarget().getClass()).info("Before " + jp.getSignature().getName());
	}

	@Pointcut("execution(* de.jonasrotert.conferenceplanner.app.repository.*.*(..))")
	public void repositoryMethods() {
	}

}
