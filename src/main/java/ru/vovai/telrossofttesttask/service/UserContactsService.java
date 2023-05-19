package ru.vovai.telrossofttesttask.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Update;
import org.springframework.stereotype.Service;
import ru.vovai.telrossofttesttask.model.User;
import ru.vovai.telrossofttesttask.model.UserContacts;

@Service
@RequiredArgsConstructor
public class UserContactsService {

    private final UserService userService;

    public UserContacts findUserContacts(Long userId){
         User user = userService.findById(userId);
         if (user != null){
             return UserContacts.builder()
                     .email(user.getEmail())
                     .phoneNumber(user.getPhoneNumber())
                     .build();
         }
         return null;
    }

    public UserContacts updateUserContacts(UserContacts userContacts, Long userId){
        User user = userService.findById(userId);
        if (user != null) {
            user.setEmail(userContacts.getEmail());
            user.setPhoneNumber(userContacts.getPhoneNumber());
            userService.updateUser(user, userId);
            return userContacts;
        }
        return null;
    }

    public boolean deleteUserContacts(Long userId){
        User user = userService.findById(userId);
        if (user != null) {
            user.setEmail(null);
            user.setPhoneNumber(null);
            userService.updateUser(user, userId);
            return true;
        }
        return false;
    }

}
