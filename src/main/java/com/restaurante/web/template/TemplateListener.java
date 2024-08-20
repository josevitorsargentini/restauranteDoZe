package com.restaurante.web.template;

import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.WebApplicationTemplateResolver;
import org.thymeleaf.web.servlet.JavaxServletWebApplication;

public class TemplateListener {

    public TemplateListener() { }

    public void contextDestroyed(ServletContextEvent sce)  { }

    public void contextInitialized(ServletContextEvent sce)  {

    	ServletContext servletContext = sce.getServletContext();
    	JavaxServletWebApplication webApplication = 
    			JavaxServletWebApplication.buildApplication(servletContext);
    	
    	WebApplicationTemplateResolver templateResolver = 
    			new WebApplicationTemplateResolver(webApplication);
    	
    	templateResolver.setTemplateMode(TemplateMode.HTML);
    	templateResolver.setPrefix("WEB-INF/templates/");
    	templateResolver.setSuffix(".html");
    	templateResolver.setCacheable(false);    	
    	
    	
    	TemplateEngine templateEngine = new TemplateEngine();
    	templateEngine.setTemplateResolver(templateResolver);    	
    	servletContext.setAttribute("templateEngine", templateEngine);
    	servletContext.setAttribute("templateWebApplication", webApplication);
    	
    	
    	Logger.getGlobal().info("Template engine OK");    	
    }
		

}
