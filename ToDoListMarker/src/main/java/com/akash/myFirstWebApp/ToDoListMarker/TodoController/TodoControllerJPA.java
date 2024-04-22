package com.akash.myFirstWebApp.ToDoListMarker.TodoController;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.akash.myFirstWebApp.ToDoListMarker.TodoRepository;
import com.akash.myFirstWebApp.ToDoListMarker.Todo.Todo;
import com.akash.myFirstWebApp.ToDoListMarker.Todo.TodoRepo;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("name")
public class TodoControllerJPA {

    TodoRepository repository;
	public TodoControllerJPA( TodoRepository repository) {
		super();
		this.repository = repository;
	}

	@RequestMapping(value = "list-todos")
	public String getAllTodos(ModelMap map) {

		String username = getLoggedInUser();
        List<Todo> todos = repository.findByAuthor(username);
		map.put("todos", todos);
		return "todos";
	}

// two way binding using a form backing object
// 1 - from bean to form
// 2 - from form to bean
	@RequestMapping(value = "add-todo", method = RequestMethod.GET)
	public String showNewToDoPage(ModelMap model) {
		String username = getLoggedInUser();
		Todo todo = new Todo(0, username, "", LocalDate.now().plusYears(1), false);
		model.put("todo", todo);
		return "todo";
	}

	@RequestMapping(value = "add-todo", method = RequestMethod.POST)
	public String addNewToDoPage(ModelMap model, @Valid Todo todo, BindingResult result) {

		if (result.hasErrors()) {
			return "todo";
		}
		String username = (String) model.get("name");
		todo.setAuthor(username);
		repository.save(todo);
		return "redirect:list-todos";
	}

	@RequestMapping(value = "delete-todo")
	public String deleteTodo(@RequestParam int id) {

		repository.deleteById(id);
		return "redirect:list-todos";
	}

	@RequestMapping(value = "update-todo", method = RequestMethod.GET)
	public String updateTodoPage(@RequestParam int id, ModelMap model) {

		Todo todo = repository.findById(id).get();
		model.addAttribute("todo", todo);
		return "updateTodo";
	}

	@RequestMapping(value = "update-todo", method = RequestMethod.POST)
	public String updateToDo(ModelMap model, @Valid Todo todo, BindingResult result) {

		if (result.hasErrors()) {
			return "todo";
		}
		String username = getLoggedInUser();
		todo.setAuthor(username);
		repository.save(todo);
		return "redirect:list-todos";
	}

	private String getLoggedInUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}

}
