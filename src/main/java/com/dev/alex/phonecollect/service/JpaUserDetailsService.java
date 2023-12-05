package com.dev.alex.phonecollect.service;

import com.dev.alex.phonecollect.model.SecureUser;
import com.dev.alex.phonecollect.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByLogin(username)
                .map(SecureUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден!"));
    }
}
