/**
 * 
 */
package com.signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.signup.bean.UserBean;
import com.signup.service.UserManagementService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author nsanda
 *
 */
@RestController
@CrossOrigin(allowedHeaders="*")
@RequestMapping("/api")
@Slf4j
public class UserRegController {
	
	@Autowired
	private UserManagementService userService;
	
	
	@PostMapping("/user-reg")
	public ResponseEntity<UserBean> register(@RequestBody UserBean user){
		log.info("the user registered data is:\t"+user.getEmail());
		
		try {
			user = userService.save(user);
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return ResponseEntity.ok(user);
	}
	
	@PostMapping("/user-reg/changePwd")
	public ResponseEntity<? extends Object> changePassword(@RequestBody UserBean user){
		log.info("the user registered data is:\t"+user.getEmail());
		
		try {
			user = userService.changePassword(user);
			return new ResponseEntity<Object>(user, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return new ResponseEntity<Object>("the inout is not sufficient", HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/user-reg/verifyAccount/{email}")
	public ResponseEntity<? extends Object> verifyEmail(@PathVariable("email") String userEmail){
		log.info("the user registered data is:\t"+userEmail);
		try {
			UserBean userBean = userService.findByEmail(userEmail);
			return new ResponseEntity<Object>(userBean, HttpStatus.OK);
		}catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return new ResponseEntity<Object>("the inout is not sufficient", HttpStatus.BAD_REQUEST);
		
	}
}
