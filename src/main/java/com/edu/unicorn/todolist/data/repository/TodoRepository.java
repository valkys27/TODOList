package com.edu.unicorn.todolist.data.repository;

import com.edu.unicorn.todolist.data.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> getAllByCompleted(boolean completed);
    @Transactional
    void deleteAllByCompletedTrue();
}
