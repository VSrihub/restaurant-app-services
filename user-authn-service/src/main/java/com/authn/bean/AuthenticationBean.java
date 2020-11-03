/**
 * 
 */
package com.authn.bean;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author nsanda
 *
 */
@Setter
@Getter
public class AuthenticationBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int loginId;
	
	private String email;
	private String password;
	private String authType;
	private boolean isAuthenticated=false;
}
