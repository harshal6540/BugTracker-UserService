package com.example.UserService.UserController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.UserService.Entity.UserEntity;
import com.example.UserService.UserRepositories.UserRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController{
    @Autowired
    UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody UserEntity userEntity){
                if(userRepository.findByEmail(userEntity.getEmail()).isPresent()){
                    return ResponseEntity.badRequest().body("User Already Present");
                }
                userEntity.setPassword(userEntity.getPassword());
                userRepository.save(userEntity);
                return ResponseEntity.ok("User Registered Successfully");
    }
}