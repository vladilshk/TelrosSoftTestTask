package ru.vovai.telrossofttesttask.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.vovai.telrossofttesttask.model.User;
import ru.vovai.telrossofttesttask.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(User user) {
        if (!userRepository.findAll().contains(user)){
            User dbUser = userRepository.save(user);
            log.info("UserService: user with id = {} was created", dbUser.getId());
            return dbUser;
        }
        return null;
    }

    public User updateUser(User user) {
        if (userRepository.findById(user.getId()).isPresent()) {
            User dbUser = userRepository.save(user);
            log.info("UserService: user with id = {} was updated", dbUser.getId());
            return dbUser;
        }
        return null;
    }

    public Boolean deleteUser(Long userId) {
        userRepository.deleteById(userId);
        log.info("UserService: user with id = {} was deleted", userId);
        return true;
    }
}
