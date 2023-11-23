package com.api.financasMaster.services;

import com.api.financasMaster.domain.user.User;
import com.api.financasMaster.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User validateSingUpUser(User user) throws Exception {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            //email ja cadastrado
            throw new Exception("email already registered");
        }else{
            // email não encontrado
            user.setId(null);
            userRepository.save(user);
            return user;
        }
    }

    public User findUserById(Integer id) throws Exception {
        return this.userRepository.findById(id).orElseThrow(() -> new Exception("user not found"));
    }

    public void saveUser(User user){
        this.userRepository.save(user);
    }
}
