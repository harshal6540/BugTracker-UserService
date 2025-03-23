package com.example.UserService.UserController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.token.Token;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.UserService.Utility.JwtUtil;

@RestController
@RequestMapping("/api/auth")
public class ValidateController {
    @Autowired
    JwtUtil jwtUtil;
    
    @GetMapping("/validate")
    public ResponseEntity validation(@RequestParam String token){
            if(jwtUtil.validateToken(token)){
                return ResponseEntity.ok("Session Validated");
            }
            return ResponseEntity.badRequest().body("Session Not Found");
    }
}
