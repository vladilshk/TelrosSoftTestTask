package ru.vovai.telrossofttesttask.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.vovai.telrossofttesttask.controller.dto.UserDto;
import ru.vovai.telrossofttesttask.controller.util.UserConverter;
import ru.vovai.telrossofttesttask.model.User;
import ru.vovai.telrossofttesttask.service.UserService;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;

import static ru.vovai.telrossofttesttask.controller.util.UserConverter.convertUserToDto;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Get all users")
    public ResponseEntity<List<UserDto>> getAllUser() {
        return ResponseEntity.ok(userService.findAll().stream().map(UserConverter::convertUserToDto).toList());
    }

    @GetMapping("/{userId}")
    @Operation(description = "Get user by id")
    public ResponseEntity<?> getUser(@PathVariable Long userId) {
        User updatedUser = userService.findById(userId);
        if (updatedUser != null) {
            return ResponseEntity.ok().body(convertUserToDto(updatedUser));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id " + userId + " not found.");
    }


    @PostMapping("/create")
    @Operation(description = "Create a new user")
    public ResponseEntity<?> createUser(@RequestBody @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMessage.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("\n");
            }
            // Вывод сообщения об ошибках валидации
            return ResponseEntity.badRequest().body(errorMessage.toString());
        }
        User createdUser;
        try {
            createdUser = userService.createUser(user);
        } catch (DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body("Email or phone number is not unique.");
        }
        if (createdUser != null) {
            return ResponseEntity.ok().body(convertUserToDto(createdUser));
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("User with this data is already exist.");
    }


    @PutMapping("/update/{userId}")
    @Operation(description = "Update user data")
    public ResponseEntity<?> updateUser(@RequestBody @Valid User user, @PathVariable Long userId, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMessage.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("\n");
            }
            // Вывод сообщения об ошибках валидации
            return ResponseEntity.badRequest().body(errorMessage.toString());
        }
        User updatedUser;
        try {
            updatedUser = userService.createUser(user);
        } catch (DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body("Email or phone number is not unique.");
        }
        if (updatedUser != null) {
            return ResponseEntity.ok().body(updatedUser);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id " + user.getId() + " not found.");
    }


    @DeleteMapping("/delete/{userId}")
    @Operation(description = "Delete user")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        Boolean isDeleted = userService.deleteUser(userId);
        if (isDeleted){
          return ResponseEntity.ok("User with id" + userId + " successfully deleted!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id " + userId + " not found.");
    }

}
