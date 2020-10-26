/**
 * 
 */
package com.signup.utils;


import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.signup.bean.UserBean;
import com.signup.model.User;

/**
 * @author nsanda
 *
 */
public class DataMapper {

	/**
	 * Converting the Bean to the Model to make it 
	 * Persistable
	 * @param userBean
	 * @return
	 */
	public static User mapBeanToModel(UserBean userBean) {
		User user = new User();
		
		BeanUtils.copyProperties(userBean, user);
		
		user.setCreatedBy("admin@restaurant.spicy.o.in");
		user.setCreatedDate(new Date());
		
		user.setModifiedBy("admin@restaurant.spicy.o.in");
		user.setModifiedDate(new Date());
		
		user.setLocked(true);
		user.setPwdChanged(false);
		
		return user;
	}
	
	
	/**
	 * Converting the persisted Model to Bean to show in UI
	 * @param user
	 * @return
	 */
	public static UserBean mapModelToBean(User user) {
		UserBean userBean = new UserBean();
		
		BeanUtils.copyProperties(user, userBean);
		userBean.setLocked(user.isLocked());
		userBean.setPwdChanged(user.isPwdChanged());
		
		return userBean;
	}
	
	
}
