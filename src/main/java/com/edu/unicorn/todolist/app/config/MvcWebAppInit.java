package com.edu.unicorn.todolist.app.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MvcWebAppInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    private static final String SERVLET_MAPPINGS = "/";

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { PersistenceUnitConfig.class, PropertiesConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { WebConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { SERVLET_MAPPINGS };
    }

}