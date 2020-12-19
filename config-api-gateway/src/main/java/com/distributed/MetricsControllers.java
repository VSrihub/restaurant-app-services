/**
 * 
 */
package com.distributed;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nsanda
 *
 */
@RestController
public class MetricsControllers {

	@GetMapping("/health-check")
	public String checkHealth() {
		return "Welcome To Zuul API Proxy Gateway";
	}
}
