package com.Secutity.SpringSecurity.Controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/hello")
    public String hello(){
        return "hello vikram kumar sangishetti";
    }

    @GetMapping("/hii")
    public String hii(){
        return "hii Vikram Kumar Sangishetti";
    }

    @GetMapping("/hey")
    public String hey(){
        return "hey Vikram Kumar Sangishetti";
    }

    @GetMapping("/csrf")
    public CsrfToken getcsrfToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute(CsrfToken.class.getName());
    }
}
