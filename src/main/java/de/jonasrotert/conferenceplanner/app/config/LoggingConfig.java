package de.jonasrotert.conferenceplanner.app.config;

import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingConfig {

	@Pointcut("execution(* de.jonasrotert.conferenceplanner.app.controller..*.*(..))")
	public void controllerMethods() {
	}

	@After("controllerMethods()")
	public void logAfterControllerMethod(final JoinPoint jp) {
		LogFactory.getLog(jp.getTarget().getClass()).info("After " + jp.getSignature().getName());
	}

	@After("redirect()")
	public void logRedirect(final JoinPoint jp) {
		LogFactory.getLog(jp.getTarget().getClass()).info("Redirecting ...");
	}

	@Before("controllerMethods()")
	public void logRepositoryMethod(final JoinPoint jp) {
		LogFactory.getLog(jp.getTarget().getClass()).info("Before " + jp.getSignature().getName());
	}

	@Pointcut("execution(org.springframework.web.servlet.view.RedirectView.new(String))")
	public void redirect() {

	}

	@Pointcut("execution(* de.jonasrotert.conferenceplanner.app.repository.*.*(..))")
	public void repositoryMethods() {
	}

}
