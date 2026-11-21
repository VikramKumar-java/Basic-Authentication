package com.Secutity.SpringSecurity.Controller;

import com.Secutity.SpringSecurity.Entity.UserEntity;
import com.Secutity.SpringSecurity.Enum.Role;
import com.Secutity.SpringSecurity.Repository.UserRepo;
import com.Secutity.SpringSecurity.DTO.AuthRequest;
import com.Secutity.SpringSecurity.Service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    JWTService jwtService;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/encodePassword")
    public void saveUserWithEncodedPassword(@RequestParam String name,
                                            @RequestParam String password,
                                            @RequestParam Role role) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(name);
        userEntity.setPassword(passwordEncoder.encode(password));
        userEntity.setIsActive(true);
        userEntity.setRole(role);

        userRepo.save(userEntity);

    }

    @PostMapping("/authenticate")
    public String authenticate(@RequestBody AuthRequest authRequest) {

        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getName(),
                authRequest.getPassword()));
        if (authenticate.isAuthenticated()) {
            String role = authenticate
                    .getAuthorities()
                    .iterator()
                    .next()
                    .getAuthority()
                    .replace("ROLE_", "");
            return jwtService.generateToken(authRequest.getName(),role);
        }
        return null;
    }
}
