package com.example.UserService.UserController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.token.Token;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.UserService.Utility.JwtUtil;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class ValidateController {
    @Autowired
    JwtUtil jwtUtil;
    
    @GetMapping("/validate")
public ResponseEntity<String> validateToken(@RequestHeader("Authorization") String authHeader) {
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing or invalid Authorization header");
    }

    String token = authHeader.substring(7); // Remove "Bearer " prefix

    if (jwtUtil.validateToken(token)) {
        return ResponseEntity.ok("Session Validated");
    }

    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Session Not Found");
}
}
