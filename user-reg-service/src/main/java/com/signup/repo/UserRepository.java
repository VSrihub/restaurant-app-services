/**
 * 
 */
package com.signup.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.signup.model.User;

/**
 * @author nsanda
 *
 */
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("from User u where u.email=:email")
	public User  fetchUserByEmail(@Param("email") String email);
}
