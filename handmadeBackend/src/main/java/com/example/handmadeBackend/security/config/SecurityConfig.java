package com.example.handmadeBackend.security.config;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Component
@Inherited
@CrossOrigin
@PropertySource("classpath:security.properties")
@Retention(value= RetentionPolicy.RUNTIME)
public @interface SecurityConfig { }
