package com.example.UserService.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import com.example.UserService.Role.*;
import org.springframework.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email=email;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
            this.password= new BCryptPasswordEncoder().encode(password);
    }
}

