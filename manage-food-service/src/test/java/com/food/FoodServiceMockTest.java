/**
 * 
 */
package com.food;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.food.bean.FoodBean;
import com.food.exception.FoodNotFound;
import com.food.model.Food;
import com.food.repo.FoodRepo;
import com.food.service.FoodService;
import com.food.service.FoodServiceImpl;

/**
 * @author nsanda
 *
 */
public class FoodServiceMockTest extends ManageFoodServiceApplicationTests {

	@Mock
	FoodRepo foodRepo;
	
	//@Mock
	@InjectMocks
	//FoodService foodService=new FoodServiceImpl(foodRepo);
	FoodService foodService;
	
	@MockBean
	Food foodModel;
	
	@MockBean
	FoodBean foodBean;
	
	@BeforeClass
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testSaveFood() throws FoodNotFound {
		FoodBean foodBeanData = new FoodBean();
		foodBeanData.setCatName("biryani");
		foodBeanData.setFoodName("monday biryani");
		foodBeanData.setId(100);
		
		when(foodService.save(foodBeanData)).thenReturn(foodBean);
		
		assertNotNull(foodBean);
		assertEquals(100, foodBean.getId());
	}
	@Test
	public void testFindFoodById() throws FoodNotFound {
		when(foodService.findCatById(100)).thenReturn(foodBean);
		System.out.println(foodBean.getId());
		System.out.println(foodBean.getCatName());
		assertNotNull(foodBean);		
		
	}
	
	
	
}
