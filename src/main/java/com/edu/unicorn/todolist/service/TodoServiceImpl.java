package com.edu.unicorn.todolist.service;

import com.edu.unicorn.todolist.data.entity.Todo;
import com.edu.unicorn.todolist.data.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("todoService")
@EnableJpaRepositories("com.edu.unicorn.todolist.data.repository")
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    @Autowired
    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public List<Todo> getAll(ListType type) {
        switch (type) {
            case ALL:
                return todoRepository.findAll();
            case ACTIVE:
                return todoRepository.getAllByCompleted(false);
            case COMPLETED:
                return todoRepository.getAllByCompleted(true);
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public Todo save(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public void delete(Todo todo) {
        todoRepository.delete(todo);
    }

    @Override
    public void clearCompleted() {
        todoRepository.deleteAllByCompletedTrue();
    }
}
