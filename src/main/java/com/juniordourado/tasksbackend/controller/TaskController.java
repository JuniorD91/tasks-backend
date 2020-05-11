package com.juniordourado.tasksbackend.controller;

import com.juniordourado.tasksbackend.model.Task;
import com.juniordourado.tasksbackend.repo.TaskRepo;
import com.juniordourado.tasksbackend.utils.DateUtils;
import com.juniordourado.tasksbackend.utils.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value ="/todo")
public class TaskController {

    @Autowired
    private TaskRepo todoRepo;

    @GetMapping
    public List<Task> findAll() {
        return todoRepo.findAll();
    }

    @PostMapping
    public ResponseEntity<Task> save(@RequestBody Task todo) throws ValidationException {
        if(todo.getTask() == null || todo.getTask() == "") {
            throw new ValidationException("Fill the task description");
        }
        if(todo.getDueDate() == null) {
            throw new ValidationException("Fill the due date");
        }
        if(!DateUtils.isEqualOrFutureDate(todo.getDueDate())) {
            throw new ValidationException("Due date must not be in past");
        }
        Task saved = todoRepo.save(todo);
        return new ResponseEntity<Task>(saved, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        todoRepo.deleteById(id);
    }
}