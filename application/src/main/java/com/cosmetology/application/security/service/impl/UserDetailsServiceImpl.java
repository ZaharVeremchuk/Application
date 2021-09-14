package com.cosmetology.application.security.service.impl;

import com.cosmetology.application.constant.ApplicationConstant;
import com.cosmetology.application.model.user.User;
import com.cosmetology.application.repository.UserRepository;
import com.cosmetology.application.security.service.impl.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private  UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(username)
                .orElseThrow(()-> new UsernameNotFoundException(ApplicationConstant.USER_NOT_FOUND_BY_USERNAME + username));

        return UserDetailsImpl.build(user);
    }

}
