/**
 * 
 */
package com.category.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.Setter;

/**
 * @author nsanda
 *
 */
@Entity
@Setter
@Getter
public class Category implements Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7032193364160823070L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String catName;
	private String patCat;
	private String catImage;
	private String status;
	
	
	private String isOffer;
	

}
