package com.akash.myFirstWebApp.ToDoListMarker.Todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import org.springframework.stereotype.Service;

//service :  talks to the repository and brings me data 
@Service
public class TodoRepo {

	private static List<Todo> todos = new ArrayList<>();
	
    public static int todoCount = 0;
    
	static {
		todos.add(new Todo(++todoCount, "in28minutes", "Learn AWS", LocalDate.now().plusYears(1), false));
		todos.add(new Todo(++todoCount, "in28minutes", "Learn Full Stack Development", LocalDate.now().plusYears(2), false));
		todos.add(new Todo(++todoCount, "in28minutes", "Learn Microservices", LocalDate.now().plusYears(3), false));

	};

	public List<Todo> getALLTodos() {
		return todos;

	}
	
	public List<Todo> findByUsername(String username){
		Predicate<? super Todo> predicate = todo -> todo.getAuthor().equalsIgnoreCase(username); 
		return todos.stream().filter(predicate).toList();

	} 
	
	public void addToDo(String username, String description, LocalDate time ,boolean done) {
		todos.add(new Todo(++todoCount, username , description, time ,false));
	}
	
	
	public void deleteById(int id) {
		//

		Predicate<? super Todo> predicate = todo -> todo.getId() == id;
		todos.removeIf(predicate);
	}
	
	public void updateTodo(Todo todo) {
      deleteById(todo.getId());
      todos.add(todo);

	}

	public Todo findById(int id) {
	   Predicate<? super Todo> predicate = todo -> todo.getId() == id;
	   Todo todo = todos.stream().filter(predicate).findFirst().get();
	   return todo;
	}

	public void updateToDo(Todo todo) {
		// TODO Auto-generated method stub
		deleteById(todo.getId());
		todos.add(todo);
		
	}
	
	

}
