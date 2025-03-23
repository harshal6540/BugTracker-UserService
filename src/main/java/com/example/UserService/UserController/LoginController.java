package com.example.UserService.UserController;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.UserService.DTO.LoginRequest;
import com.example.UserService.DTO.LoginResponse;
import com.example.UserService.Entity.UserEntity;
import com.example.UserService.UserRepositories.UserRepository;
import com.example.UserService.Utility.JwtUtil;

@RestController
@RequestMapping("/api/auth")
public class LoginController {
    
 
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        System.out.println("LOGIN CONTROLLERRR CALLLLEEDDD FORRR USSSEERRRNAAMEEE =========="+loginRequest.getUsername());
        Optional<UserEntity> user = userRepository.findByUsername(loginRequest.getUsername());
        
        //

        if (user.isPresent() && passwordEncoder.matches(loginRequest.getPassword(), user.get().getPassword())) {
            String token = jwtUtil.generateToken(user.get().getUsername());
            System.out.println(token);
            return ResponseEntity.ok(new LoginResponse(token));
        }
        return ResponseEntity.status(401).body("Invalid Credentials");
    }
}
