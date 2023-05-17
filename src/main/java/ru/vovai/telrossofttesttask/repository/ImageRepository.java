package ru.vovai.telrossofttesttask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vovai.telrossofttesttask.model.Image;
import ru.vovai.telrossofttesttask.model.User;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long > {
    Optional<Image> findByUser(User user);

    void deleteByUser(User user);
}
