package com.api.financasMaster.controller.serverControllers;

import com.api.financasMaster.domain.user.User;
import com.api.financasMaster.dto.LoginRequest;
import com.api.financasMaster.dto.UserDTO;
import com.api.financasMaster.dto.UserTransactionDTO;
import com.api.financasMaster.services.UserService;import com.api.financasMaster.services.exceptions.UserNotFoundException;
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
    public ResponseEntity<Integer> singIn(@RequestBody LoginRequest loginRequest) {
        try {
            var userId = userService.validateLogin(loginRequest);
            return new ResponseEntity<>(userId,HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<UserTransactionDTO> getUser(@PathVariable Integer id){
        try{
            User user = userService.findUserById(id);
            UserTransactionDTO userTransactionDTO = userService.convertUser(user);
            return new ResponseEntity<>(userTransactionDTO,HttpStatus.OK);
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}
