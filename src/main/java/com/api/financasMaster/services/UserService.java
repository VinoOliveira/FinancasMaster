package com.api.financasMaster.services;

import com.api.financasMaster.domain.transaction.TransactionType;
import com.api.financasMaster.domain.user.User;
import com.api.financasMaster.dto.LoginRequest;
import com.api.financasMaster.dto.TransactionDTO;
import com.api.financasMaster.dto.UserDTO;
import com.api.financasMaster.dto.UserTransactionDTO;
import com.api.financasMaster.repositories.UserRepository;
import com.api.financasMaster.services.exceptions.EmailAlreadyRegisteredException;
import com.api.financasMaster.services.exceptions.EmailNotFoundException;
import com.api.financasMaster.services.exceptions.IncorrectPasswordException;
import com.api.financasMaster.services.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Lazy
    @Autowired
    private TransactionService transactionService;

    //valida se o email informado no ato do cadastro ja existe no banco de dados
    public User validateSingUpUser(UserDTO data) throws EmailAlreadyRegisteredException {
        if (userRepository.findByEmail(data.email()).isPresent()) {
            throw new EmailAlreadyRegisteredException("Email already registered");
        } else {
            User user = new User(data);
            user.setBalance(BigDecimal.valueOf(0));
            userRepository.save(user);
            return user;
        }
    }
    //valida se as informações passadas para fazer login estão de acordo com os dados cadastrados
    public Integer validateLogin(LoginRequest loginRequest) throws EmailNotFoundException, IncorrectPasswordException {
        User user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new EmailNotFoundException("Email not found"));

        if (!loginRequest.getPassword().equals(user.getPassword())) {
            throw new IncorrectPasswordException("Incorrect password");
        }else{
            return user.getId();
        }
    }
    public User findUserById(Integer id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    //converte um objeto do tipo User em UserTransactionDTO
    public UserTransactionDTO convertUser (User user){
        List<TransactionDTO> transactions = transactionService.findTransactionByUserId(user.getId());
        BigDecimal totalProfit = transactionService.sum(transactionService.findTransactionsByTypeToday(user.getId(), TransactionType.PROFIT));
        BigDecimal totalExpense = transactionService.sum(transactionService.findTransactionsByTypeToday(user.getId(), TransactionType.EXPENSE));
        return new UserTransactionDTO(user,totalProfit,totalExpense,transactions);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

}
