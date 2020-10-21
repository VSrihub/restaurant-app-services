/**
 * 
 */
package com.signup;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author nsanda
 *
 */
@Setter
@Getter
public class Auth0Response implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1930697838289305719L;
	
	private String _id;
	private boolean email_verified;
	private String email;
	private String given_name;
}
