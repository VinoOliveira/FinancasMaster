package com.api.financasMaster.controller.serverControllers;

import com.api.financasMaster.domain.user.User;
import com.api.financasMaster.dto.LoginRequest;
import com.api.financasMaster.dto.UserDTO;
import com.api.financasMaster.dto.UserTransactionDTO;
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

    @PostMapping("/singUp")
    public ResponseEntity<User> singUp(@RequestBody UserDTO user) {
        try {
            User newUser = userService.validateSingUpUser(user);
            return new ResponseEntity<>(newUser,HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<UserTransactionDTO> singIn(@RequestBody LoginRequest loginRequest) {
        try {
            var user = userService.validateLogin(loginRequest);
            return new ResponseEntity<>(user,HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
