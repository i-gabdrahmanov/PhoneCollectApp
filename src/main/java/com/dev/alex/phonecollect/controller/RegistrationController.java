package com.dev.alex.phonecollect.controller;

import com.dev.alex.phonecollect.model.SecureUser;
import com.dev.alex.phonecollect.model.UserDTO;
import com.dev.alex.phonecollect.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/reg")
    public String getRegisterPage() {
        return "register";
    }

    @PostMapping("/sign_up")
    public ResponseEntity<SecureUser> register(UserDTO userCandidate) {
        return ResponseEntity.ok(userService.saveUser(userCandidate)
                .map(SecureUser::new)
                .orElseThrow(() -> new RuntimeException("Register Ecxeption")));
    }
}
