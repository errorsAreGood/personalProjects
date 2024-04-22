package com.restApiUsingSpringBoot.api.usersController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.restApiUsingSpringBoot.api.PostRepository;
import com.restApiUsingSpringBoot.api.UserRepository;
import com.restApiUsingSpringBoot.api.users.Post;
import com.restApiUsingSpringBoot.api.users.User;

import jakarta.validation.Valid;

@RestController
public class UserControllerJPA {
	UserRepository repository ;
    PostRepository postRepo;
	public UserControllerJPA(UserRepository repository, PostRepository postRepo) {
		super();
		this.repository = repository;
		this.postRepo = postRepo;
	}
	

	@GetMapping("/users")
	public MappingJacksonValue getAllUsers() {
		List<User> allUsers = repository.findAll();
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(allUsers);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept();
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		mappingJacksonValue.setFilters(filters);
		
		return mappingJacksonValue;

	}

	@GetMapping("/users/{id}")
	public EntityModel<User> findById(@PathVariable int id) {

		Optional<User> user = repository.findById(id);
		if (user == null) {
			throw new UserNotFoundException("id : " + id);
		}
        // HATEOAS : HYPERMEDIA as the engine of Application State .
		// HATEOAS : EntityModel and WebMvcLinkBuilder
		// EntityModel is a wrapper class around User, we don't want to alter the user
		// class hence
		// you can add links to the entityModel .
		// WebMvcLinkBuilder : you can help building the link using this .
		// entityModel is just a wrapper class , you can add links or WebLinks to it .

		EntityModel<User> entityModel = EntityModel.of(user.get());

		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getAllUsers());
		entityModel.add(link.withRel("all-users"));
//		entityModel.add(link.withSelfRel());
		
		return entityModel;
	}

	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {

		User savedUser = repository.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();

	}

	@DeleteMapping("/users/{id}")
	public void deleteById(@PathVariable int id) {
		repository.deleteById(id);

	}
	
	//POSTS 
	@GetMapping("/users/{id}/posts")
	public List<Post> retrieveAllPostsByUserId(@PathVariable int id){
		Optional<User> user = repository.findById(id);
		
		if(user == null) {
			throw new UserNotFoundException("bhakk benchod");
		}
	   return user.get().getPosts();
	}	
	
	
	@GetMapping("/users/posts")
	public List<List<Post> > retrieveAllPosts(){
		List<User> allUsers = repository.findAll();
		
		List<List<Post>> allPost = new ArrayList<List<Post>>();
		
		for(User user : allUsers) {
			List<Post> postsPerUser = user.getPosts();
			allPost.add(postsPerUser);
		}
		
		return allPost;	
	}
	
	@GetMapping("users/{id}/posts/{post_id}")
	public List<Post> retrievePostByUser(@PathVariable int id, @PathVariable int post_id ) {
		
		List<Post> postlist = retrieveAllPostsByUserId(id);
		List<Post> result = new ArrayList<Post>();
	    for(Post post : postlist) {
	    	if(post.getId() == post_id) {
	    		result.add(post);
	    	}
	    }
	    return result;
	}
	
	
	
	
	
	
	
}
