package org.web.mywebsite.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.web.mywebsite.entities.UserEntity;
import org.web.mywebsite.exceptions.CommonException;
import org.web.mywebsite.repositories.UserRepository;

import java.util.Optional;

import static org.web.mywebsite.enums.ResponseCode.NOT_FOUND;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserEntity> userOptional = userRepository.findUserByEmail(email);
        if (userOptional.isEmpty()) throw new CommonException(NOT_FOUND, "Email hoặc mật khẩu không đúng!");
        else return userOptional.get();
    }
}
