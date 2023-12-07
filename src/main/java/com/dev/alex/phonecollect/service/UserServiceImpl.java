package com.dev.alex.phonecollect.service;

import com.dev.alex.phonecollect.model.SecureUser;
import com.dev.alex.phonecollect.model.User;
import com.dev.alex.phonecollect.model.UserDTO;
import com.dev.alex.phonecollect.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Optional<User> saveUser(UserDTO userCandidate) {
        if (userRepository.findUserByLogin(userCandidate.getUsername()).isPresent()) {
            throw new RuntimeException("Пользователь уже существует");
        }
            User user = new User();
            user.setLogin(userCandidate.getUsername());
            user.setPassword(passwordEncoder.encode(userCandidate.getPassword()));
            user.setRole(userCandidate.getRole());
        return Optional.of(userRepository.save(user));
    }
}
