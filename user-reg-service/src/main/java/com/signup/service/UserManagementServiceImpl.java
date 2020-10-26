/**
 * 
 */
package com.signup.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.signup.Auth0Response;
import com.signup.OutboundCommunicator;
import com.signup.bean.EmailBean;
import com.signup.bean.UserBean;
import com.signup.model.User;
import com.signup.repo.UserRepository;
import com.signup.utils.DataMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * @author nsanda
 *
 */
@Service
@Slf4j
public class UserManagementServiceImpl implements UserManagementService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private OutboundCommunicator outbound;
	
	/* (non-Javadoc)
	 * @see com.signup.service.UserManagementService#save(com.signup.bean.UserBean)
	 */
	@Override
	public UserBean save(UserBean userBean) {
		
		User userModel = DataMapper.mapBeanToModel(userBean);
		try {
			/**
			 * Step-1: Create User in Auth0 IDP
			 */
			Auth0Response auth0Resp = outbound.registerUser(userModel);
			if(auth0Resp.get_id() != null && !auth0Resp.get_id().isEmpty()) {
				
				/**
				 * step-2: Create User in our Database
				 */
				userModel = userRepo.save(userModel);
				if(userModel.getId()> 0) {
					EmailBean mailBean = new EmailBean();
					mailBean.setMailTo(userModel.getEmail());
					mailBean.setMailFrom("admin@restaurant.spicy.co.in");
					mailBean.setMailSubject("Verification Email");
					mailBean.setMailType("VerificationEmail");
					ObjectMapper mapper = new ObjectMapper();
					String mailJson = mapper.writeValueAsString(mailBean);
					outbound.sendVerificationEmail(mailJson);
				}
				
			}else {
				//throw an exception with the reason for failure
				
				//and roll back the transaction
				log.info("User Regsration fail in IDP and in DB ");
			}
			
		}catch (Exception e) {
			log.error(e.getMessage());
		}	
		
		return DataMapper.mapModelToBean(userModel);
	}

	/* (non-Javadoc)
	 * @see com.signup.service.UserManagementService#findById(int)
	 */
	@Override
	public UserBean findById(int Id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.signup.service.UserManagementService#findByEmail(java.lang.String)
	 */
	@Override
	public UserBean findByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.signup.service.UserManagementService#changePassword(com.signup.bean.UserBean)
	 */
	@Override
	public UserBean changePassword(UserBean userBean) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.signup.service.UserManagementService#verifyAccount(java.lang.String)
	 */
	@Override
	public UserBean verifyAccount(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.signup.service.UserManagementService#findAll()
	 */
	@Override
	public List<UserBean> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
