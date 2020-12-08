/**
 * 
 */
package com.jwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import com.jwt.service.AppUserAuthenticationService;

/**
 * @author nsanda
 *
 */
@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private AppUserAuthenticationService jwtTkenService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		
		http
		.authorizeRequests()
		.antMatchers(HttpMethod.POST, "/api/authn/**")
		.permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.apply(new JwtAuthenticationConfigurer(jwtTkenService))
		.and()
		.csrf().disable()
		.httpBasic().disable()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		
		
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/swagger-doc.html/**");
	}
}
