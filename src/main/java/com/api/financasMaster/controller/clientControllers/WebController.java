package com.api.financasMaster.controller.clientControllers;

import com.api.financasMaster.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String loginPage(){
        return "LoginPage";
    }
}
