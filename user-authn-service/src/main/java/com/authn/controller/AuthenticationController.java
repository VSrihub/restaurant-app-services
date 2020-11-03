/**
 * 
 */
package com.authn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authn.bean.AuthenticationBean;
import com.authn.service.AuthnService;
import com.authn.utils.AuthnConstants;

/**
 * @author nsanda
 *
 */
@RestController
@CrossOrigin(allowedHeaders="*")
@RequestMapping("/api")
public class AuthenticationController {

	@Autowired
	private AuthnService authnService;
	
	@PostMapping("/authenticate")
	public ResponseEntity<? extends Object> doLogin(@RequestBody AuthenticationBean authnBean){
		if(AuthnConstants.IDP_AUTHN_TYPE.equalsIgnoreCase(authnBean.getAuthType())) {
			//do IDP Authentication
		}else {
			authnBean = authnService.doLogin(authnBean);
			
		}
		
		return ResponseEntity.ok(authnBean);
	}
}
