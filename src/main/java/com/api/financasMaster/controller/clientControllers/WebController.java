package com.api.financasMaster.controller.clientControllers;

import com.api.financasMaster.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Controller
public class WebController {

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String loginPage(){
        return "LoginPage";
    }
    @GetMapping("/singUp")
    public String singUpPage(){
        return "Sign-upPage";
    }
    @GetMapping("/profile")
    public String profilePage(){
        return "ProfilePage";
    }
    @GetMapping("/register")
    public String registerPage(){
        return "RegisterPage";
    }
    @GetMapping("/home")
    public String homePage(){
        return "TransactionPage";
    }
}
