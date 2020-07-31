package com.product.order.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("{noop}user123").roles("USER", "ADMIN").and()
				.withUser("test").password("{noop}test123").roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
        .httpBasic()
        .and()
        .authorizeRequests()
        .antMatchers(HttpMethod.GET, "/orders/getAll").hasRole("USER")
        .antMatchers(HttpMethod.GET, "/orders/get/**").hasRole("USER")
        .antMatchers(HttpMethod.POST, "/orders/add").hasRole("ADMIN")
        .antMatchers(HttpMethod.PUT, "/orders/update").hasRole("ADMIN")
        .antMatchers(HttpMethod.DELETE, "/orders/delete/**").hasRole("ADMIN")
        .antMatchers(HttpMethod.GET, "/products/getAll").hasRole("USER")
        .antMatchers(HttpMethod.GET, "/products/get/**").hasRole("USER")
        .antMatchers(HttpMethod.POST, "/products/add").hasRole("ADMIN")
        .antMatchers(HttpMethod.PUT, "/products/update").hasRole("ADMIN")
        .antMatchers(HttpMethod.DELETE, "/products/delete/**").hasRole("ADMIN")
        .and()
        .csrf().disable()
        .formLogin().disable();
	}

}
