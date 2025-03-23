package com.example.UserService.UserController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.UserService.Entity.UserEntity;
import com.example.UserService.Services.UserService;
import com.example.UserService.UserRepositories.UserRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController{
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;
    

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody UserEntity userEntity){
                System.out.println("Inside SignUp");
                if(userRepository.findByEmail(userEntity.getEmail()).isPresent()){
                    return ResponseEntity.badRequest().body("User Already Present");
                }
                userEntity.setPassword(userEntity.getPassword());
                userService.registerUser(userEntity);
                return ResponseEntity.ok("User Registered Successfully");
    }
}