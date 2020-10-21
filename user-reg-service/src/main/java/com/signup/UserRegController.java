/**
 * 
 */
package com.signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.extern.slf4j.Slf4j;

/**
 * @author nsanda
 *
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class UserRegController {

	@Autowired
	private OutboundCommunicator outbound;
	
	
	@PostMapping("/user-reg")
	public ResponseEntity<Auth0Response> register(@RequestBody UserRegBean user){
		log.info("the user registered data is:\t"+user.getEmail());
		Auth0Response userModel = null;
		try {
			userModel = outbound.registerUser(user);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.ok(userModel);
	}
}
