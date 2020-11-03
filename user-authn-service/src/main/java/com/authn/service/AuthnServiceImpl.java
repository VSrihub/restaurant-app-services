/**
 * 
 */
package com.authn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.authn.bean.AuthenticationBean;
import com.authn.model.User;
import com.authn.repo.AuthenticationRepo;

import lombok.extern.slf4j.Slf4j;

/**
 * @author nsanda
 *
 */
@Service
@Slf4j
public class AuthnServiceImpl implements AuthnService {

	@Autowired
	private AuthenticationRepo authnRepo;
	/* (non-Javadoc)
	 * @see com.authn.service.AuthnService#doLogin(com.authn.bean.AuthenticationBean)
	 */
	@Override
	public AuthenticationBean doLogin(AuthenticationBean authnBean) {
		User userModel = authnRepo.doLogin(authnBean.getEmail(), authnBean.getPassword());
		log.info("userModel is "+userModel.getPassword());
		if(userModel != null) {
			authnBean.setAuthenticated(true);
		}
		return authnBean;
	}

}
