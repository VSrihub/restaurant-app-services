/**
 * 
 */
package com.category.bean;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author nsanda
 *
 */
@Setter
@Getter
public class CategoryBean implements Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7032193364160823070L;

	private int id;
	
	private String catName;
	private String patCat;
	private String catImage;
	private String status;
	private boolean isOffer;
	

}
