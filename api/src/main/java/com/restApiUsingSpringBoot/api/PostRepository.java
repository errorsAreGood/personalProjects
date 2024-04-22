package com.restApiUsingSpringBoot.api;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restApiUsingSpringBoot.api.users.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{

}
