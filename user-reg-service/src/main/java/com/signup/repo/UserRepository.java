/**
 * 
 */
package com.signup.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.signup.model.User;

/**
 * @author nsanda
 *
 */
public interface UserRepository extends JpaRepository<User, Integer> {

}
