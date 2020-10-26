package com.notification.bean;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NotificationBean implements Serializable{

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
