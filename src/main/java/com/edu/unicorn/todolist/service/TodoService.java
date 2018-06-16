package com.edu.unicorn.todolist.service;

import com.edu.unicorn.todolist.data.entity.Todo;

import java.util.List;

public interface TodoService {
    List<Todo> getAll(ListType type);
    Todo save(Todo todo);
    void delete(Todo todo);
    void clearCompleted();
}
