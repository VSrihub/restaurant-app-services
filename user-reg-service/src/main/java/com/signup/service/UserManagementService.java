/**
 * 
 */
package com.signup.service;

import java.util.List;

import com.signup.bean.UserBean;

/**
 * @author nsanda
 *
 */
public interface UserManagementService {

	public UserBean save(UserBean userBean);
	
	public UserBean findById(int Id);
	public UserBean findByEmail(String email);
	
	public UserBean changePassword(UserBean userBean);
	public UserBean verifyAccount(String email);
	
	public List<UserBean>  findAll();
}
