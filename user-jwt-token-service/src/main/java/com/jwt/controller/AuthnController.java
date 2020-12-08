/**
 * 
 */
package com.jwt.controller;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.bean.AppUserBean;
import com.jwt.config.CustomAuthenProvider;
import com.jwt.service.AppUserAuthenticationService;

/**
 * @author nsanda
 *
 */
@RestController
@RequestMapping("/api")
public class AuthnController {

	@Autowired
	private CustomAuthenProvider authnProvider;
	
	@Autowired
	private AppUserAuthenticationService jwtService;
	
	
	@PostMapping("/authn/token")
	public ResponseEntity<? extends Object> authenticate(@RequestBody AppUserBean user){
		Authentication auth = authnProvider
							.authenticate(new UsernamePasswordAuthenticationToken(
									user.getUserName(),
									user.getPassword()));
		JSONObject jsonBuilder = new JSONObject();
		
		if(auth.isAuthenticated()) {
			String token = jwtService.createToken(auth.getName(), auth.getAuthorities());
			jsonBuilder.put("authorization_token", token);
			jsonBuilder.put("type", "Bearer");
			return new ResponseEntity(jsonBuilder.toString(),HttpStatus.OK);
		}else {
			return new ResponseEntity("Un Authirzed. Please check the Credentials Passed",
					HttpStatus.UNAUTHORIZED);
		}
		
	}
	
	@GetMapping("/userInfo")
	public ResponseEntity<? extends Object> getUserFromToken(HttpServletRequest request){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		JSONObject jsonBuilder = new JSONObject();
		if(auth != null) {
			try {
				UserDetails userDetails = jwtService.getUserByToken(request);
				return new ResponseEntity(userDetails,HttpStatus.OK);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	@GetMapping("/validateToken")
	public ResponseEntity<? extends Object> validateToken(HttpServletRequest request){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		JSONObject jsonBuilder = new JSONObject();
		if(auth != null) {
			
			String token = jwtService.resolveToken(request);
			jsonBuilder.put("authorization_token", token);
			jsonBuilder.put("type", "Bearer");
			if(auth.isAuthenticated()) {
				jsonBuilder.put("isValid", true);
			}else {
				jsonBuilder.put("isValid", false);
			}
		}else {
			jsonBuilder.put("isValid", false);
			jsonBuilder.put("reason", "token is invalid or tampered");
			
		}
		
		return new ResponseEntity(jsonBuilder.toString(),HttpStatus.OK);
	}
}
