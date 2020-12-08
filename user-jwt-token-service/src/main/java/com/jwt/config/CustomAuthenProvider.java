/**
 * 
 */
package com.jwt.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.jwt.model.AppUser;
import com.jwt.repo.AppUserRepo;

import lombok.extern.slf4j.Slf4j;

/**
 * @author nsanda
 *
 */
@Service
@Slf4j
public class CustomAuthenProvider implements AuthenticationManager {

	@Autowired
	private AppUserRepo appUserRepo;
	
	/* (non-Javadoc)
	 * @see org.springframework.security.authentication.AuthenticationManager#authenticate(org.springframework.security.core.Authentication)
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String userName = authentication.getName();
		log.info("User Name is:\t"+userName);
		
		String password = authentication.getCredentials().toString();
		log.info("Password is:\t"+password);
		
		AppUser appUser = appUserRepo.loadUserByName(userName);
		if(null != password && password.equals(appUser.getPassword())) {
			List<GrantedAuthority> rolesList = new ArrayList<GrantedAuthority>();
			rolesList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			authentication = new UsernamePasswordAuthenticationToken(userName, null, rolesList);
			return authentication;
		}
		
		
		
		return null;
	}

}
