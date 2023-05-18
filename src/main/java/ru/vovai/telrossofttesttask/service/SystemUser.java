package ru.vovai.telrossofttesttask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.vovai.telrossofttesttask.repository.SystemUserRepository;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class SystemUser {
    private final PasswordEncoder passwordEncoder;
    private final SystemUserRepository repository;
    @Value("${default.username}")
    private String defaultUsername;
    @Value("${default.password}")
    private String defaultPassword;

    @PostConstruct
    private void addDefaultUser() {
        if (repository.findByUsername(defaultUsername).isEmpty()) {
            repository.save(ru.vovai.telrossofttesttask.model.SystemUser.builder()
                    .username(defaultUsername)
                    .password(passwordEncoder.encode(defaultPassword))
                    .build());
        }

    }
}
