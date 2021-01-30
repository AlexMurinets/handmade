package com.example.handmadeBackend.security.config;

import com.example.handmadeBackend.security.jwt.JwtAuthenticationEntryPoint;
import com.example.handmadeBackend.security.jwt.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SecurityConfig
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    @Value("${jwt.permit.all}")
    private final String[] publicPermit;

    @Value("${cors.allowed.methods}")
    private final String[] corsMethods;

    @Value("${cors.allowed.headers}")
    private final String[] corsHeaders;

    @Value("${cors.allowed.origins}")
    private final String [] allowedOrigins;

    @Value("${resources.handler}")
    private final String [] resourceHandler;

    @Value("${resources.locations}")
    private final String[] locations;

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    private final UserDetailsService jwtUserDetailsService;

    private final JwtRequestFilter jwtRequestFilter;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(resourceHandler)
                .addResourceLocations(locations);
    }

    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(allowedOrigins)
                .allowedMethods(corsMethods)
                .allowedHeaders(corsHeaders);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors()
                .and()
                .csrf()
                .disable()
                .headers()
                .frameOptions()
                .deny()
                .and()
                .authorizeRequests().antMatchers(publicPermit).permitAll().
                        anyRequest().authenticated().and().
                        exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public JavaMailSenderImpl mailSender(){
        return new JavaMailSenderImpl();
    }
}

