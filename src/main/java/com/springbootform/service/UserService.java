package com.springbootform.service;

import com.springbootform.dao.UserRepository;
import com.springbootform.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity registerUser(String login, String password, String email) {
        if (login == null && password == null) {
            return null;
        }

        if (userRepository.findFirstByLogin(login).isPresent()) {
            System.out.println("This login already exists");
            return null;
        }

        UserEntity userEntity = new UserEntity(login, password, email);
        return userRepository.save(userEntity);
    }

    public UserEntity authenticate(String login, String password) {
        return userRepository.findByLoginAndPassword(login, password);
    }

}
