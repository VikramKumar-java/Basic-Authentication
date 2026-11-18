package com.Secutity.SpringSecurity.Controller;

import com.Secutity.SpringSecurity.Entity.UserEntity;
import com.Secutity.SpringSecurity.Repository.UserRepo;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/encodePassword")
    public void saveUserWithEncodedPassword(@RequestParam String name,
                                            @RequestParam String password){
        UserEntity userEntity = new UserEntity();
        userEntity.setName(name);
        userEntity.setPassword(passwordEncoder.encode(password));
        userEntity.setIsActive(true);

        userRepo.save(userEntity);

    }
}
