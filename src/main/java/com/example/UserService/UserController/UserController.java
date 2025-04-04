package com.example.UserService.UserController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.UserService.Entity.UserEntity;
import com.example.UserService.Services.UserService;
import com.example.UserService.UserRepositories.UserRepository;
import com.example.UserService.Utility.JwtUtil;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtUtil jwtUtil;

    @GetMapping("/currentUser")
    public ResponseEntity<?> currentUser(@RequestHeader("Authorization") String authHeader){
        String token= authHeader.substring(7);
        String loggedInUsername = jwtUtil.extractUsername(token);
        return ResponseEntity.ok().body(loggedInUsername);
    }
    
    
    @GetMapping("/all")
public ResponseEntity<List<UserEntity>> getAllUsers(@RequestHeader("Authorization") String authHeader ) {
    List<UserEntity> users = userRepository.findAll();
    return ResponseEntity.ok(users);
}

@GetMapping("/{id}")
public ResponseEntity<?> getUser(@RequestHeader ("Authorization") String authHeader ,@PathVariable Long id){
    Optional<UserEntity> user=userRepository.findById(id);
    if(user.isPresent()){
        return ResponseEntity.ok().body(user);
    }
    else{
        return ResponseEntity.badRequest().body("User Not Found");
    }
}
}
