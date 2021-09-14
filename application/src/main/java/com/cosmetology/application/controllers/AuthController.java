package com.cosmetology.application.controllers;

import com.cosmetology.application.dto.request.LoginDTO;
import com.cosmetology.application.dto.request.SignUpDto;
import com.cosmetology.application.security.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signIn")
    public ResponseEntity<?> signIn(@Valid @RequestBody LoginDTO loginDTO){
        return ResponseEntity.status(HttpStatus.OK)
                .body(authService.signIn(loginDTO));
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpDto signUpDto){
        return ResponseEntity.status(HttpStatus.OK)
                .body(authService.registerUser(signUpDto));
    }

}
