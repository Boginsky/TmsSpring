package by.boginsky.controllers;

import by.boginsky.annotations.Authenticational;
import by.boginsky.domain.Todo;
import by.boginsky.domain.plaintobjects.TodoPojo;
import by.boginsky.exceptions.CustomException;
import by.boginsky.services.ITodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class TodoController {

    private final ITodoService todoService;

    private Long userId;

    @Autowired
    public TodoController (ITodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping(path = "/user/{userId}/todo")
    @Authenticational
    public ResponseEntity<TodoPojo> createTodo (HttpServletRequest request, @RequestBody Todo todo) {
        TodoPojo result = todoService.createTodo(todo, userId);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @Authenticational
    @GetMapping(path = "/user/{userId}/todo/{id}")
    public ResponseEntity<TodoPojo> getTodo (HttpServletRequest request, @PathVariable Long id) {
        return new ResponseEntity<>(todoService.getTodo(id, userId), HttpStatus.OK);
    }

    @Authenticational
    @GetMapping(path = "/user/{userId}/todos")
    public ResponseEntity<List<TodoPojo>> getAllTodo (HttpServletRequest request) {
        return new ResponseEntity<>(todoService.getAllTodos(userId), HttpStatus.OK);
    }

    @Authenticational
    @PutMapping(path = "/user/{userId}/todo/{id}")
    public ResponseEntity<TodoPojo> updateTodo (HttpServletRequest request, @RequestBody Todo source, @PathVariable Long id) {
        return new ResponseEntity<>(todoService.updateTodo(source, id, userId), HttpStatus.OK);
    }

    @Authenticational
    @DeleteMapping(path = "/user/{userId}/todo/{id}")
    public ResponseEntity<String> deleteTodo (HttpServletRequest request, @PathVariable Long id) {
        return new ResponseEntity<>(todoService.deleteTodo(id, userId), HttpStatus.OK);
    }

    /**
     * Exception handling
     */

    @ExceptionHandler
    public ResponseEntity<String> onMissingTodoName(DataIntegrityViolationException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ClassUtils.getShortName(exception.getClass()) + " Todo with such name already exists");
    }

    @ExceptionHandler
    public ResponseEntity<String> onMissingTodoId(NoSuchElementException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ClassUtils.getShortName(exception.getClass()) + ": no such todo");
    }

    @ExceptionHandler
    public ResponseEntity<String> onMissingTodo(EmptyResultDataAccessException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ClassUtils.getShortName(exception.getClass()) + ": cant find any todo");
    }

    @ExceptionHandler
    public ResponseEntity<String> SQLProblems(SQLException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ClassUtils.getShortName(exception.getClass())
                + " " + exception.getSQLState() + " "
                + exception.getLocalizedMessage()
                +  " : sqlException with todo");
    }

    @ExceptionHandler
    public ResponseEntity<String> customExceptionHandler (CustomException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ClassUtils.getShortName(exception.getClass()) + " " + exception.getCause() + " " + exception.getLocalizedMessage());
    }
}
