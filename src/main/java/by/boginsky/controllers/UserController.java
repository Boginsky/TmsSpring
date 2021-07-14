package by.boginsky.controllers;

import by.boginsky.domain.User;
import by.boginsky.domain.plaintobjects.UserPojo;
import by.boginsky.exceptions.CustomException;
import by.boginsky.security.TokenManager;
import by.boginsky.security.TokenPayload;
import by.boginsky.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.*;

@RestController
public class UserController {

    private final IUserService userService;
    private final TokenManager tokenManager;

    @Autowired
    public UserController(IUserService userService, TokenManager tokenManager) {
        this.userService = userService;
        this.tokenManager = tokenManager;
    }

    @GetMapping(path = "/")
    public ModelAndView login(HttpSession session) {
        return new ModelAndView("login", HttpStatus.OK);
    }

    @GetMapping(path = "/registration")
    public ModelAndView createUser(Model model) {
        model.addAttribute("userForm", new User());
        return new ModelAndView("registration", HttpStatus.OK);
    }

    @PostMapping(path = "/registration")
    public ModelAndView registration(@ModelAttribute("userForm") User userForm, Map<String, Object> map) {

//        map.put("email",что сюда);
//        map.put("password",что сюда);

        userService.createUser(userForm);

        return new ModelAndView("login", HttpStatus.OK);
    }

    @GetMapping(path = "/authentication")
    public ModelAndView authenticateUser(@RequestBody User user) {
        UserPojo authenticatedUser = userService.findUserByEmailAndPassword(user.getEmail(), user.getPassword());

        if (authenticatedUser == null) {
            return new ModelAndView("error", HttpStatus.UNAUTHORIZED);
        }

        String token = tokenManager.createToken(new TokenPayload(
                authenticatedUser.getId(),
                authenticatedUser.getEmail(),
                Calendar.getInstance().getTime()));

        return new ModelAndView("/user/{userId}/todo/{id}", HttpStatus.OK); //название представления
    }

    @GetMapping(path = "/user/{id}")
    public ResponseEntity<UserPojo> getUser(@PathVariable Long id) {
        UserPojo result = userService.getUser(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //    @Authenticational
    @GetMapping(path = "/users")
    public ResponseEntity<List<UserPojo>> getUser() {
        List<UserPojo> result = userService.getAllUsers();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //    @Authenticational
    @PutMapping(path = "/user/{id}")
    public ResponseEntity<UserPojo> updateUser(@RequestBody User source, @PathVariable Long id) {
        UserPojo result = userService.updateUser(source, id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //    @Authenticational
    @DeleteMapping(path = "/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.OK);
    }

    /**
     * EXCEPTION HANDLING
     */

    @ExceptionHandler
    public ResponseEntity<String> onConflictUserEmail(DataIntegrityViolationException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ClassUtils.getShortName(exception.getClass()) + " User with such email already exists");
    }

    @ExceptionHandler
    public ResponseEntity<String> onMissingUserId(NoSuchElementException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ClassUtils.getShortName(exception.getClass()) + ": no such user");
    }

    @ExceptionHandler
    public ResponseEntity<String> onMissingUser(EmptyResultDataAccessException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ClassUtils.getShortName(exception.getClass()) + " " + exception.getLocalizedMessage() + ": cant find any user");
    }

    @ExceptionHandler
    public ResponseEntity<String> SQLProblems(SQLException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ClassUtils.getShortName(exception.getClass())
                + " " + exception.getSQLState() + " "
                + exception.getLocalizedMessage()
                + " : sqlException with user");
    }

    @ExceptionHandler
    public ResponseEntity<String> customExceptionHandler(CustomException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ClassUtils.getShortName(exception.getClass()) + " " + exception.getCause() + " " + exception.getLocalizedMessage());
    }
}
