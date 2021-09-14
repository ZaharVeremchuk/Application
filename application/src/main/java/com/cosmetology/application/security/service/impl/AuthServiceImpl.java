package com.cosmetology.application.security.service.impl;

import com.cosmetology.application.constant.ApplicationConstant;
import com.cosmetology.application.dto.request.LoginDTO;
import com.cosmetology.application.dto.request.SignUpDto;
import com.cosmetology.application.dto.response.JwtResponse;
import com.cosmetology.application.dto.response.MessageResponseDTO;
import com.cosmetology.application.model.enums.AccessRole;
import com.cosmetology.application.model.role.Role;
import com.cosmetology.application.model.user.User;
import com.cosmetology.application.repository.RoleRepository;
import com.cosmetology.application.repository.UserRepository;
import com.cosmetology.application.security.AuthService;
import com.cosmetology.application.security.jwt.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Override
    public JwtResponse signIn(LoginDTO loginDTO) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return JwtResponse.builder()
                .token(jwt)
                .id(userDetails.getId())
                .username(userDetails.getUsername())
                .email(userDetails.getEmail())
                .roles(roles)
                .build();
    }

    @Override
    public ResponseEntity<?> registerUser(SignUpDto signUpDto) {
        MessageResponseDTO messageResponseDTO = new MessageResponseDTO();

        if (userRepository.existsByUsername(signUpDto.getUsername())) {
            messageResponseDTO.setMessage(ApplicationConstant.USER_NOT_FOUND + signUpDto.getUsername());
            return ResponseEntity.badRequest()
                    .body(messageResponseDTO);
        }
        if (userRepository.existsByEmail(signUpDto.getEmail())) {
            messageResponseDTO.setMessage(ApplicationConstant.USER_NOT_FOUND + signUpDto.getEmail());
            return ResponseEntity.badRequest()
                    .body(messageResponseDTO);
        }

        User user = new User(signUpDto.getName(),
                             signUpDto.getSurname(),
                             signUpDto.getUsername(),
                             signUpDto.getEmail(),
                             signUpDto.getPassword());

        Set<String> strRoles = signUpDto.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {

            Role userRole = roleRepository.findByName(AccessRole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(AccessRole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException(ApplicationConstant.ROLE_NOT_FOUND));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(AccessRole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException(ApplicationConstant.ROLE_NOT_FOUND));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(AccessRole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException(ApplicationConstant.ROLE_NOT_FOUND));
                        roles.add(userRole);
                }
            });
        }



        user.setRoles(roles);
        userRepository.save(user);
        messageResponseDTO.setMessage(ApplicationConstant.USER_SUCCESSFULLY_REGISTERED);
        return ResponseEntity.ok(messageResponseDTO);

    }

}
