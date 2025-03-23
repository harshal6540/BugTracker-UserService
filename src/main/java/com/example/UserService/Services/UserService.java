package com.example.UserService.Services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.UserService.Entity.UserEntity;
import com.example.UserService.UserRepositories.UserRepository;

@Service
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
            this.userRepository=userRepository;
            this.passwordEncoder=passwordEncoder;
    }

    public void registerUser(UserEntity user){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
    }

}
