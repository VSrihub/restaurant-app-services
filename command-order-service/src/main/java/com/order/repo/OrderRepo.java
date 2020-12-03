/**
 * 
 */
package com.order.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.order.model.Order;

/**
 * @author nsanda
 *
 */
public interface OrderRepo extends JpaRepository<Order, Integer> {

	@Query("from Order o where o.orderRunNumber=:runNumber")
	public Order findOrderByRunNumber(@Param("runNumber") String runNumber);
}
