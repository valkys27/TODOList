package com.edu.unicorn.todolist.app.controller;

import com.edu.unicorn.todolist.data.entity.Todo;
import com.edu.unicorn.todolist.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping(value = "/todo", headers = "Accept=application/json")
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/list")
    public ModelAndView list() {
        return new ModelAndView("index");
    }

    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Todo> getAll() {
        return todoService.getAll(ListType.ALL);
    }

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public Todo save(@RequestBody Todo todo) {
        return todoService.save(todo);
    }

    @PostMapping(value = "/delete")
    public void delete(@RequestBody Todo todo) {
        todoService.delete(todo);
    }

    @GetMapping(value = "/clearCompleted")
    public void clearCompleted() {
        todoService.clearCompleted();
    }
}
