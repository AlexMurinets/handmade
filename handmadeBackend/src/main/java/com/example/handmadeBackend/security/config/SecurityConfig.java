package com.example.handmadeBackend.security.config;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.lang.annotation.Inherited;

@Component
@PropertySource("classpath:security.properties")
@CrossOrigin
@Inherited
public @interface SecurityConfig { }
