package com.example.chat.service;

import com.example.chat.dto.LoginRequestDTO;
import com.example.chat.dto.RegisterRequestDTO;
import com.example.chat.dto.UserDTO;
import com.example.chat.persistence.user.User;
import com.example.chat.persistence.user.UserGateway;
import com.example.chat.util.SignUp;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    private final UserGateway userGateway;
    private final Map<Integer,String> userToken;
    public UserService(UserGateway userGateway) {
        this.userGateway = userGateway;
        this.userToken = new HashMap<>();
    }

    public SignUp createUser(RegisterRequestDTO registerRequestDTO) {
        if (checkIfUserExist(registerRequestDTO.email())) {
            User newUser = new User(0, registerRequestDTO.first_name(),
                    registerRequestDTO.second_name(),
                    registerRequestDTO.email(),
                    registerRequestDTO.password());
            userGateway.addUser(newUser);
            return SignUp.SUCCESS;
        }

        return SignUp.FAIL;
    }

    public Optional<User> getUsers(int id) {
        return userGateway.selectUserById(id);
    }

    public Optional<User> getUserByEmail(String email) {
        return userGateway.getUserByEmail(email);
    }

    public UserDTO login(LoginRequestDTO loginRequestDTO) {
        // validate
        Optional<User> optionalUser= validateUser(loginRequestDTO.email(), loginRequestDTO.password());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            String token = generateToken();
            userToken.put(user.id(),token);
            return new UserDTO(user.id(),user.first_name(), user.second_name());
        }
        return null;
    }
    private Optional<User> validateUser(String email, String password) {
        return userGateway.authenticateUser(email,password);
    }
    private boolean checkIfUserExist(String email) {
        return userGateway.getUserByEmail(email).isEmpty();
    }

    private String generateToken() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[20];
        random.nextBytes(bytes);
        return bytes.toString();
    }
}
