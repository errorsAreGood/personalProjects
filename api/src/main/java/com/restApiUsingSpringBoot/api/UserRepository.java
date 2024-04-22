package com.restApiUsingSpringBoot.api;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restApiUsingSpringBoot.api.users.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
