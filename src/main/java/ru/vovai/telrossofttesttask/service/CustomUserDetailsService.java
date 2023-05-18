package ru.vovai.telrossofttesttask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.vovai.telrossofttesttask.model.SystemUser;
import ru.vovai.telrossofttesttask.repository.SystemUserRepository;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final SystemUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SystemUser user = repository.findByUsername(username).orElseThrow();
        //returned User is from spring lib
        return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }
}
