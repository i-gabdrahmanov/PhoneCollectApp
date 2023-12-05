package com.dev.alex.phonecollect.repository;

import com.dev.alex.phonecollect.model.Phone;
import com.dev.alex.phonecollect.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> getUserById(Long id);
    Optional<User> findFirstByLoginIgnoreCase(String login);

    Optional<User> findUserByLogin(String id);

}
