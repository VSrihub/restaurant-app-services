/**
 * 
 */
package com.food.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.food.bean.FoodBean;
import com.food.service.FoodService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author nsanda
 *
 */
@RestController
@CrossOrigin(allowedHeaders="*")
@RefreshScope
@RequestMapping("/api")
@Slf4j
public class FoodController {

	@Autowired
	private FoodService foodService;
	
	@Value("${todays.food.available.hours}")
	private String todayavailableHours;
	
	@PostMapping("/food")
	public ResponseEntity<? extends Object> addfood(@RequestBody FoodBean foodBean){
		try {
			foodBean = foodService.save(foodBean);			
		}catch (Exception e) {
			return new ResponseEntity<Object>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Object>(foodBean, HttpStatus.OK);
	}
	
	@PutMapping("/food")
	public ResponseEntity<? extends Object> updatefood(@RequestBody FoodBean foodBean){
		try {
			foodBean = foodService.save(foodBean);			
		}catch (Exception e) {
			return new ResponseEntity<Object>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Object>(foodBean, HttpStatus.OK);
	}
	
	@GetMapping("/food")
	public ResponseEntity<? extends Object> getAllCategories(){
		try {
			log.info("food will be availabel today for just :\t"+todayavailableHours+":\thours");
			List<FoodBean> foodBeanList = foodService.findAll();		
			return new ResponseEntity<Object>(foodBeanList, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<Object>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	@DeleteMapping("/food/{foodId}")
	public ResponseEntity<? extends Object>  deletefood(@PathVariable("foodId") int foodId){
		log.info("food id is:\t"+foodId);;
		try {
			List<FoodBean> foodBeanList = foodService.deleteFood(foodId);
			return new ResponseEntity<Object>(foodBeanList, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<Object>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/food/{foodId}")
	public ResponseEntity<? extends Object>  foodById(@PathVariable("foodId") int foodId){
		log.info("food id is:\t"+foodId);;
		try {
			FoodBean foodBean = foodService.findCatById(foodId);
			return new ResponseEntity<Object>(foodBean, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<Object>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
