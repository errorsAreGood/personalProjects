package com.restApiUsingSpringBoot.api.users;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

@Service
public class UserService  {
     
	public static List<User> usersList = new ArrayList<User>();
	public static int userCount = 0;
	
	static {
		usersList.add(new User(++userCount , "Akash", LocalDate.now()));
		usersList.add(new User(++userCount , "Palak", LocalDate.now().plusYears(1)));
		usersList.add(new User(++userCount , "Sagar", LocalDate.now().plusYears(2)));
		usersList.add(new User(++userCount , "Eesh", LocalDate.now().plusYears(3)));


	}
	
	public List<User> getAllUsers(){
		return usersList;
		
	}
	
	public User findOne(int id) {
		
		Predicate<? super User> predicate = user -> user.getId() == id;
		return usersList.stream().filter(predicate).findFirst().orElse(null);
	}
	
	public User save(User user) {
		
		user.setId(++userCount);
		usersList.add(user);
		System.out.println(userCount);
		return user;
	}

	public void deleteById(int id) {
		Predicate<? super User> predicate = user -> user.getId() == id;		
		usersList.removeIf(predicate);
	}
}
