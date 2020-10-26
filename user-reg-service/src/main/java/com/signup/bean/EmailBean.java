/**
 * 
 */
package com.signup.bean;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author nsanda
 *
 */
@Setter
@Getter
public class EmailBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;

	private String mailFrom;
	private String mailTo;
	private String mailHeader;
	private String mailSubject;
	private String mailBody;
	private String mailType;

}
