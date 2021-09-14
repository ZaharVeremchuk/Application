package com.cosmetology.application.security;

import com.cosmetology.application.dto.request.LoginDTO;
import com.cosmetology.application.dto.request.SignUpDto;
import com.cosmetology.application.dto.response.JwtResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Service
public interface AuthService {

    JwtResponse signIn(LoginDTO loginDTO);

    ResponseEntity<?> registerUser(@Valid @RequestBody SignUpDto signUpDto);
}
