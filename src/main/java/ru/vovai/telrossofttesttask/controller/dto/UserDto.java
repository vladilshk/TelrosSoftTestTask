package ru.vovai.telrossofttesttask.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String name;
    private String surname;
    private String middleName;
    private Date birthday;
    private String email;
    private String phoneNumber;
}
