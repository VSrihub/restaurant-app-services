/**
 * 
 */
package com.signup;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.signup.model.User;

import lombok.extern.slf4j.Slf4j;

/**
 * @author nsanda
 *
 */
@Service
@Slf4j
public class OutboundCommunicator {

	@Value("${client_id}")
	private String clientId;
	
	@Value("${connection}")
	private String connection;
	
	public Auth0Response registerUser(User user) throws JsonProcessingException {
		RestTemplate rt = new RestTemplate();
		ObjectMapper mapper = new ObjectMapper();
		Auth0UserModel userModel = convertToModel(user);
		String userModelJson = mapper.writeValueAsString(userModel);
		log.info("userModelJson is :\t"+userModelJson);
				
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");
		
		HttpEntity<String> request = new HttpEntity<>(userModelJson, headers);
		
		String authoResp = rt.postForObject("https://dev-3s3dknax.auth0.com/dbconnections/signup",
				request, String.class);
		
		
		log.info("response from autho is:\t"+authoResp);
		
		Auth0Response auth0Resp = mapper.readValue(authoResp, Auth0Response.class);
		
		return auth0Resp;
	}
	
	private Auth0UserModel convertToModel(User user) {
		Auth0UserModel userModel = new Auth0UserModel();
		userModel.setClient_id(clientId);
		userModel.setConnection(connection);
		userModel.setEmail(user.getEmail());
		userModel.setPassword(user.getPassword());
		userModel.setUsername(user.getUserId());
		userModel.setGiven_name(user.getFname()+" "+user.getLname());
		return userModel;
	}
	
	public boolean sendVerificationEmail(String emailData) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");
		RestTemplate rt = new RestTemplate();
		HttpEntity<String> request = new HttpEntity<>(emailData, headers);
		
		String emailresp = rt.postForObject("http://localhost:6052/notification-service/api/mail",
				request, String.class);
		
		if(emailresp != null) {
			return true;
		}
		
		return false;
	}
}
