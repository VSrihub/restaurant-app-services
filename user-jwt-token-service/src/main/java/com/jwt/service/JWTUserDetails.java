/**
 * 
 */
package com.jwt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jwt.model.AppUser;
import com.jwt.repo.AppUserRepo;

/**
 * @author nsanda
 *
 */
@Service
public class JWTUserDetails implements UserDetailsService {

	@Autowired
	private AppUserRepo appUserRepo;
	
	
	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser appUser = appUserRepo.loadUserByName(username);
		if(appUser != null) {
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			
			UserDetails userDetails = new User(appUser.getUserId(), 
					appUser.getPassword(), authorities);
			
			
			return userDetails;
		}
		
		
		return null;
	}

}
