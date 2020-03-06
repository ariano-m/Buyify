package com.buyify.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.authentication.AuthenticationProvider;

public interface UserRepository extends JpaRepository<User, Long>{

    User findByUsername(String username);

    User findByName(String name);

}