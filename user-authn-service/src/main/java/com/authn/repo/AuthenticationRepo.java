/**
 * 
 */
package com.authn.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.authn.model.User;

/**
 * @author nsanda
 *
 */
public interface AuthenticationRepo extends JpaRepository<User, Integer> {

	@Query("from User u where u.email=:email and u.password=:password")
	public User doLogin(@Param("email") String email, @Param("password") String password);
}
