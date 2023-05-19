package ru.vovai.telrossofttesttask.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.vovai.telrossofttesttask.model.User;
import ru.vovai.telrossofttesttask.repository.UserRepository;

import javax.transaction.Transactional;
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

    @Transactional
    public User updateUser(User user, Long userId) {
        User userFromDb = userRepository.findById(userId).orElse(null);
        if (userFromDb != null) {
            user.setId(userId);
            user.setImage(user.getImage());
            User updatedUser = userRepository.save(user);
            log.info("UserService: user with id = {} was updated", updatedUser.getId());
            return updatedUser;
        }
        return null;
    }

    public Boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            log.info("UserService: user with id = {} was deleted", userId);
            return true;
        }
        return false;
    }
}
