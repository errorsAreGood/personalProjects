package com.akash.myFirstWebApp.ToDoListMarker;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akash.myFirstWebApp.ToDoListMarker.Todo.Todo;

public interface TodoRepository extends JpaRepository<Todo, Integer>{
	
	public List<Todo> findByAuthor(String username);

}
