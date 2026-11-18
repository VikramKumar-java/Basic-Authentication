package com.Secutity.SpringSecurity.Service;

import com.Secutity.SpringSecurity.Entity.UserEntity;
import com.Secutity.SpringSecurity.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepo userRepo;

    public UserEntity getUserByUserName(String name) {
        return userRepo.findByNameAndIsActive(name, true)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }


    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

     UserEntity user = getUserByUserName(name);
        return User.builder()
                .username(user.getName())
                .password(user.getPassword())
                .authorities(Collections.emptyList())
                .build();
    }
}
