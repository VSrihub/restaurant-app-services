package com.jwt.config;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jwt.service.AppUserAuthenticationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtAuthenticationConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
	private AppUserAuthenticationService jwtAuthenticationService;

	public JwtAuthenticationConfigurer(AppUserAuthenticationService jwtAuthenticationService) {
		this.jwtAuthenticationService = jwtAuthenticationService;
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		log.info("i am in JwtAuthenticationConfigurer");
		//FmpAuthnFilter jwtAuthenticationFilter = new FmpAuthnFilter(jwtAuthenticationService);
		AppUserAuthnFilter jwtAuthenticationFilter = new AppUserAuthnFilter(jwtAuthenticationService);
		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}
}