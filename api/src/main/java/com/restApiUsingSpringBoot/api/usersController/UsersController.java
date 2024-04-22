//package com.restApiUsingSpringBoot.api.usersController;
//
//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
//
//import java.net.URI;
//import java.util.List;
//
//import org.springframework.hateoas.EntityModel;
//import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.converter.json.MappingJacksonValue;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//
//import com.fasterxml.jackson.databind.ser.FilterProvider;
//import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
//import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
//import com.restApiUsingSpringBoot.api.users.User;
//import com.restApiUsingSpringBoot.api.users.UserService;
//
//import jakarta.validation.Valid;
//
//@RestController
//public class UsersController {
//
//	UserService userService;
//
//	public UsersController(UserService userService) {
//		super();
//		this.userService = userService;
//	}
//
//	@GetMapping("/users")
//	public MappingJacksonValue getAllUsers() {
//        
//		
//		List<User> allUsers = userService.getAllUsers();
//		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(allUsers);
//		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept();
//		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
//		mappingJacksonValue.setFilters(filters);
//		
//		return mappingJacksonValue;
//
//	}
//
//	@GetMapping("/users/{id}")
//	public EntityModel<User> findById(@PathVariable int id) {
//
//		User user = userService.findOne(id);
//		if (user == null) {
//			throw new UserNotFoundException("id : " + id);
//		}
//        // HATEOAS : HYPERMEDIA as the engine of Application State .
//		// HATEOAS : EntityModel and WebMvcLinkBuilder
//		// EntityModel is a wrapper class around User, we don't want to alter the user
//		// class hence
//		// you can add links to the entityModel .
//		// WebMvcLinkBuilder : you can help building the link using this .
//		// entityModel is just a wrapper class , you can add links or WebLinks to it .
//
//		EntityModel<User> entityModel = EntityModel.of(user);
//
//		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getAllUsers());
//		entityModel.add(link.withRel("all-users"));
////		entityModel.add(link.withSelfRel());
//		
//		return entityModel;
//	}
//
//	@PostMapping("/users")
//	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
//
//		User savedUser = userService.save(user);
//		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
//				.toUri();
//		return ResponseEntity.created(location).build();
//
//	}
//
//	@DeleteMapping("/users/{id}")
//	public void deleteById(@PathVariable int id) {
//		userService.deleteById(id);
//
//	}
//
//}
