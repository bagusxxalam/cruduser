package net.blueapple.presentation;

import net.blueapple.domain.user.User;
import net.blueapple.domain.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by Lithium on 9/15/2017.
 */
@RestController
@Validated
@RequestMapping(path = "/user")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<User> findAll(Pageable pageable) {
        return userService.findAll(pageable);
    }
    @RequestMapping(path = "/{username}",method = RequestMethod.GET)
    public User findByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }

    @RequestMapping(method = RequestMethod.POST)
    public User add(
        @Size(min = 1,message = "Username min 1")@RequestParam String username,
        @Size(min = 5,message = "Password min 5")@RequestParam String password,
        @Size(min = 8,message = "Email min 8")@RequestParam String email,
        @Size(min = 5,message = "Telephone min 7")@RequestParam String telephone) {
        return userService.add(new User(username,password,email,telephone));
    }

    @RequestMapping(path = "/delete",method = RequestMethod.POST)
    public RestResponse delete(@RequestParam String username) {
        userService.delete(userService.findByUsername(username));
        return new RestResponse(200,"User "+username+" deleted");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestResponse> handle(Exception e) throws AccessDeniedException {
        if(e instanceof AccessDeniedException) throw (AccessDeniedException)e;
        e.printStackTrace();
        return new ResponseEntity<>(new RestResponse(500,
                "We can't proccess your request"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // will catch any validation exception annotated with @Validated
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<RestResponse> handle(ConstraintViolationException e) {
        StringBuilder sb=new StringBuilder();
        e.getConstraintViolations().forEach(violation -> {
            sb.append("==> "+violation.getMessage()+"\n");
        });
        //e.printStackTrace();
        return new ResponseEntity<>(new RestResponse(HttpStatus.BAD_REQUEST.value(),sb.toString()),
                HttpStatus.BAD_REQUEST);
    }
}
