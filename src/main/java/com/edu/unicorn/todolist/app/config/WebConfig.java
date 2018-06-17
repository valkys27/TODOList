package com.edu.unicorn.todolist.app.config;

import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan("com.edu.unicorn.todolist")
public class WebConfig extends WebMvcConfigurationSupport {

    private static final String PREFIX = "/WEB-INF/";
    private static final String SUFFIX = ".html";
    private static final String RESOURCE_HANDLER = "/resources/**";
    private static final String RESOURCE_LOCATIONS = "/resources/";

    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix(PREFIX);
        resolver.setSuffix(SUFFIX);
        return resolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler(RESOURCE_HANDLER)
                .addResourceLocations(RESOURCE_LOCATIONS);
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
