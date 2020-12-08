/**
 * 
 */
package com.jwt.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.jwt.service.AppUserAuthenticationService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author nsanda
 *
 */
@Slf4j
public class AppUserAuthnFilter extends GenericFilterBean {
	
	private AppUserAuthenticationService authnService;
	
	public AppUserAuthnFilter(AppUserAuthenticationService authnService){
		this.authnService = authnService;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.info("FmpAuthnFilter....");
		
		//Authentication auth = null;
		try {
			Authentication auth  = authnService.authentication((HttpServletRequest)request);
			SecurityContextHolder.getContext().setAuthentication(auth);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			log.error(e.getMessage());
		}
		//log.info("is authenticated already>:\t"+auth.isAuthenticated());
		
		chain.doFilter(request, response);
		
	}

}
