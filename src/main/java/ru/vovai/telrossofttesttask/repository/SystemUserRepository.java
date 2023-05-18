package ru.vovai.telrossofttesttask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vovai.telrossofttesttask.model.SystemUser;

import java.util.Optional;

public interface SystemUserRepository extends JpaRepository<SystemUser, Long> {
    Optional<SystemUser> findByUsername(String username);
}
