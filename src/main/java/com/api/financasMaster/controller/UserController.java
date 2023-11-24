package com.api.financasMaster.controller;

import com.api.financasMaster.domain.user.User;
import com.api.financasMaster.dto.LoginRequest;
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
    public ResponseEntity<User> getUser(@PathVariable Integer id){
        try {
            User user = userService.findUserById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @PostMapping("/singUp")
    public ResponseEntity<User> singUp(@RequestBody User user) {
        try {
            userService.validateSingUpUser(user);
            return new ResponseEntity<>(user,HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @PostMapping("/singIn")
    public ResponseEntity<User> singIn(@RequestBody LoginRequest loginRequest) {
        try {
            var user = userService.validateLogin(loginRequest);
            return new ResponseEntity<>(user,HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
