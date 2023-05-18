package ru.vovai.telrossofttesttask.controller.util;

import ru.vovai.telrossofttesttask.controller.dto.UserDto;
import ru.vovai.telrossofttesttask.model.User;

public class UserConverter {

    public static UserDto convertUserToDto(User user){
        return UserDto.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .middleName(user.getMiddleName())
                .birthday(user.getBirthday())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }
}
