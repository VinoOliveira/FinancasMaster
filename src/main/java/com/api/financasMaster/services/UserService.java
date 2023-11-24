package com.api.financasMaster.services;

import com.api.financasMaster.domain.user.User;
import com.api.financasMaster.dto.LoginRequest;
import com.api.financasMaster.repositories.UserRepository;
import com.api.financasMaster.services.exceptions.EmailAlreadyRegisteredException;
import com.api.financasMaster.services.exceptions.EmailNotFoundException;
import com.api.financasMaster.services.exceptions.IncorrectPasswordException;
import com.api.financasMaster.services.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class UserService {
    @Autowired
    private UserRepository userRepository;


    public User validateSingUpUser(User user) throws Exception {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            //email ja cadastrado
            throw new EmailAlreadyRegisteredException("email already registered");
        } else {
            // email não encontrado, procede com cadastro
            user.setId(null);
            userRepository.save(user);
            return user;
        }
    }

    public User validateLogin(LoginRequest loginRequest) throws Exception {
        var user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new EmailNotFoundException("email not found"));
        System.out.println("Stored Password: " + user.getPassword());
        System.out.println("Provided Password: " + loginRequest.getPassword());
        if (user.getPassword().equals(loginRequest.getPassword())) {
            return user;
        } else {
            throw new IncorrectPasswordException("Incorrect password.");
        }
    }

    public User findUserById(Integer id) throws Exception {
        return this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("user not found"));
    }

    public void saveUser(User user) {
        this.userRepository.save(user);
    }
}
