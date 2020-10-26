/**
 * 
 */
package com.signup.bean;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

/**
 * @author nsanda
 *
 */
@Setter
@Getter
public class UserBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	/**
	 * For Identity
	 */
	private String fname;
	private String lname;
	private String email;
	private String userId;
	private String password;
	private String cpassword;
	private Long mobile;
	
	/**
	 * For access
	 */
	private boolean isLocked;
	private boolean isPwdChanged;
	
	/**
	 * For Auditing
	 */
	/*private String createdBy;
	private Date createdDate;
	private String modifiedBy;
	private Date modifiedDate;*/
}
