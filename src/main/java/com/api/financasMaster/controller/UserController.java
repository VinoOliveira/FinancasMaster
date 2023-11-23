package com.api.financasMaster.controller;

import com.api.financasMaster.domain.user.User;
import com.api.financasMaster.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id) throws Exception {
       User user = userService.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @PostMapping("/singUp")
    public ResponseEntity<User> postUser(@RequestBody User user) throws Exception {
        userService.validateSingUpUser(user);
        return new ResponseEntity<>(user,HttpStatus.CREATED);
    }
}
