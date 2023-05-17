package ru.vovai.telrossofttesttask.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.vovai.telrossofttesttask.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
