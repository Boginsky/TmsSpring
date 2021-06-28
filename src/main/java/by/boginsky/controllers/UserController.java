package by.boginsky.controllers;

import by.boginsky.domain.User;
import by.boginsky.domain.plaintobjects.UserPojo;
import by.boginsky.exceptions.CustomException;
import by.boginsky.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/registration")
    public ResponseEntity<UserPojo> createUser(@RequestBody User user) {
        UserPojo result = userService.createUser(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(path = "/user/{id}")
    public ResponseEntity<UserPojo> getUser(@PathVariable Long id) {
        UserPojo result = userService.getUser(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(path = "/users")
    public ResponseEntity<List<UserPojo>> getUser() {
        List<UserPojo> result = userService.getAllUsers();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping(path = "/user/{id}")
    public ResponseEntity<UserPojo> updateUser(@PathVariable Long id, @RequestBody User source){
        UserPojo result = userService.updateUser(source,id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping(path = "/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        return new ResponseEntity<>(userService.deleteUser(id),HttpStatus.OK);
    }

    /**
     * EXCEPTION HANDLING
     */

    @ExceptionHandler
    public ResponseEntity<String> onConflictUserEmail(DataIntegrityViolationException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ClassUtils.getShortName(exception.getClass()) + " User with such email already exists");
    }

    @ExceptionHandler
    public ResponseEntity<String> onMissingUserId(NoSuchElementException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ClassUtils.getShortName(exception.getClass()) + ": no such user");
    }

    @ExceptionHandler
    public ResponseEntity<String> onMissingUser(EmptyResultDataAccessException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ClassUtils.getShortName(exception.getClass()) + " " + exception.getLocalizedMessage() + ": cant find any user");
    }

    @ExceptionHandler
    public ResponseEntity<String> SQLProblems(SQLException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ClassUtils.getShortName(exception.getClass())
                + " " + exception.getSQLState() + " "
                + exception.getLocalizedMessage()
                +  " : sqlException with user");
    }

    @ExceptionHandler
    public ResponseEntity<String> customExceptionHandler (CustomException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ClassUtils.getShortName(exception.getClass()) + " " + exception.getCause() + " " + exception.getLocalizedMessage());
    }
}
