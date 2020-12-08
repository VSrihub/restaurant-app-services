/**
 * 
 */
package com.jwt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jwt.model.AppUser;

/**
 * @author nsanda
 *
 */
public interface AppUserRepo extends JpaRepository<AppUser, Integer> {

	//@Query("from AppUser user where user.userId=:userId")
	@Query("from AppUser user where user.email=:userId")
	public AppUser loadUserByName(@Param("userId") String userId);
	
	
}
