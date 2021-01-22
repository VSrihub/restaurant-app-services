/**
 * 
 */
package com.food.exception;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * @author nsanda
 *
 */
@Setter
@Getter
public class FoodApiErrorList {

	private List<FoodApiError> errorList;
}
