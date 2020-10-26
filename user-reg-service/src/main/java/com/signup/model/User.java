/**
 * 
 */
package com.signup.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

/**
 * @author nsanda
 *
 */
@Entity
@Setter
@Getter
public class User implements Serializable {

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
	private String createdBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	private String modifiedBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;

}
