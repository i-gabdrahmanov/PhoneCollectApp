package com.dev.alex.phonecollect.service;

import com.dev.alex.phonecollect.model.User;
import com.dev.alex.phonecollect.model.UserDTO;

import java.util.Optional;

public interface UserService {
    Optional<User> saveUser(UserDTO userCandidate);
}
