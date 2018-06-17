package com.edu.unicorn.todolist.app.config;

import org.springframework.context.annotation.*;

@ComponentScan(basePackages = {"com.edu.unicorn.todolist.service"})
@PropertySource(value = {"classpath:jdbc_test.properties"})
public class TestPropertiesConfig {}
