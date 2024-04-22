package com.akash.myFirstWebApp.ToDoListMarker.Todo;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

@Entity
public class Todo {
    
	@Id
	@GeneratedValue
	int id;
	String author;
	
	@Size(min=10, message="Enter atleast 10 characters")
	String description;
	
	LocalDate targetDate;
	boolean done;
	public Todo(){
		
	}

	public Todo(int id, String author, String description, LocalDate targetDate, boolean done) {
		super();
		this.id = id;
		this.author = author;
		this.description = description;
		this.targetDate = targetDate;
		this.done = done;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(LocalDate targetDate) {
		this.targetDate = targetDate;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", author=" + author + ", description=" + description + ", targetDate=" + targetDate
				+ ", done=" + done + "]";
	}

}
