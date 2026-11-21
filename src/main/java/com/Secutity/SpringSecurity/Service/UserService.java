package com.Secutity.SpringSecurity.Service;

import com.Secutity.SpringSecurity.Entity.UserEntity;
import com.Secutity.SpringSecurity.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.task.TaskExecutionProperties;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepo userRepo;

    public UserEntity getUserByUserName(String name) {

        UserEntity user = userRepo.findByNameAndIsActive(name, true)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));

        return user;
    }


    @Override
    public UserDetails loadUserByUsername(String name)
            throws UsernameNotFoundException {

        UserEntity user = getUserByUserName(name);
        List<SimpleGrantedAuthority> authorities= new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().name()));
        user.getRole().getPermessions().forEach(permession ->
                authorities.add(new SimpleGrantedAuthority(permession.name())));

        return User.builder()
                .username(user.getName())
                .password(user.getPassword())
                .authorities(authorities)
                .build();
    }
}
