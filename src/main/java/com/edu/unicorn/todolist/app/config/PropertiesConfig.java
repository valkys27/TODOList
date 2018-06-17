package com.edu.unicorn.todolist.app.config;

import org.springframework.context.annotation.*;

@Configuration
@PropertySource(value = {"classpath:jdbc.properties"})
public class PropertiesConfig {}
