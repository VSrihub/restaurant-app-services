/**
 * 
 */
package com.order.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.order.model.Order;

/**
 * @author nsanda
 *
 */
public interface OrderRepo extends JpaRepository<Order, Integer> {

}
