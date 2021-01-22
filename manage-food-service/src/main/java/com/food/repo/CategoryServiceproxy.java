/**
 * 
 */
package com.food.repo;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author nsanda
 *
 */
//@RibbonClient
//@FeignClient(name="category-service",url="localhost:6053/api/category")
//@FeignClient(name="category-service")
@FeignClient(name="MANAGE-CATEGORY-SERVICE")
//@FeignClient(name="CONFIG-API-GATEWAY")
//MANAGE-CATEGORY-SERVICE
public interface CategoryServiceproxy {

	//@GetMapping(value="/gateway/MANAGE-CATEGORY-SERVICE/api/category/{catId}",consumes=MediaType.APPLICATION_JSON_VALUE)
	@GetMapping(value="/api/category/{catId}",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<? extends Object>  categoryById(@PathVariable("catId") String catId);
}
