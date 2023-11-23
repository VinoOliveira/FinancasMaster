package com.api.financasMaster.services;

import com.api.financasMaster.domain.user.User;
import com.api.financasMaster.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User validateSingUpUser(String firstName,String lastName,String email, String password) throws Exception {
        if (userRepository.findByEmail(email).isPresent()) {
            //email ja cadastrado
            throw new Exception("email already registered");
        }else{
            // email não encontrado
            return new User(firstName,lastName,email,password);
        }
    }

    public User findUserById(Integer id) throws Exception {
        return this.userRepository.findById(id).orElseThrow(() -> new Exception("user not found"));
    }

    public void saveUser(User user){
        this.userRepository.save(user);
    }
}
