/**
 * 
 */
package com.authn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.authn.bean.AuthenticationBean;
import com.authn.service.AuthnService;
import com.authn.utils.AuthnConstants;

import lombok.extern.slf4j.Slf4j;

/**
 * @author nsanda
 *
 */
@RestController
@CrossOrigin(allowedHeaders="*")
@RequestMapping("/api")
@Slf4j
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
	
	@PostMapping("/gatewayToken")
	public String getAuthTokenFromIdp(@RequestBody AuthenticationBean user) {
		log.info("UserAuthnController::getAuthToken()");
		
		String data = "{\"username\":"+"\""+user.getEmail()+"\""+",\"password\":"+"\""+user.getPassword()+"\""+",\"grant_type\":\"password\",\"scope\":\"openid email offline_access\"}";
		log.info("fmpToken data is:\t"+data);
		RestTemplate rt = new RestTemplate();
		/*HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Basic ZWlUZUJuTUNZMThORnZjZmQ4eEdBa0wybnFhY1FkR2M6cWYwM3ZyYUJKOTdaLVNwcVJwLVZxcVBxQy1Sd0pQc1RfOEliYTRSbkZZUy1HWWlheGpyVlFRSVllVWlETHA5VQ==");      
		headers.set("Content-Type", "application/json");*/
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");
		headers.set("Authorization", "Basic ZWlUZUJuTUNZMThORnZjZmQ4eEdBa0wybnFhY1FkR2M6cWYwM3ZyYUJKOTdaLVNwcVJwLVZxcVBxQy1Sd0pQc1RfOEliYTRSbkZZUy1HWWlheGpyVlFRSVllVWlETHA5VQ==");
		
		HttpEntity<String> request = new HttpEntity<>(data, headers);
		try {
			/*String response = rt.postForObject("https://dev-3s3dknax.auth0.com/oauth/token",
					data, 
					String.class, 
					headers);*/
			String response = rt.postForObject("https://dev-3s3dknax.auth0.com/oauth/token", request, String.class); 
			
			System.out.println("response is:\t"+response);
			return response;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
