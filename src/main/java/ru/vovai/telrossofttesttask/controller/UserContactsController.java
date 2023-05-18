package ru.vovai.telrossofttesttask.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.vovai.telrossofttesttask.model.UserContacts;
import ru.vovai.telrossofttesttask.service.UserContactsService;

import javax.validation.Valid;

@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class UserContactsController {

    private final UserContactsService userContactsService;

    //NO need to make create method because contacts in my program are part of user

    @GetMapping("/{userId}")
    @Operation(description = "Get user by id")
    public ResponseEntity<?> getUserContacts(@PathVariable Long userId) {
        UserContacts userContacts = userContactsService.findUserContacts(userId);
        if (userContacts != null) {
            return ResponseEntity.ok().body(userContacts);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id " + userId + " not found.");
    }


    @PutMapping("/update/{userId}")
    @Operation(description = "Update user data")
    public ResponseEntity<?> updateUserContacts(@RequestBody @Valid UserContacts userContacts, @PathVariable Long userId, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMessage.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("\n");
            }
            // Вывод сообщения об ошибках валидации
            return ResponseEntity.badRequest().body(errorMessage.toString());
        }

        UserContacts updatedUserContacts;
        try {
            updatedUserContacts = userContactsService.updateUserContacts(userContacts, userId);
        } catch (DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body("Email or phone number is not unique.");
        }
        if (updatedUserContacts != null) {
            return ResponseEntity.ok().body(updatedUserContacts);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id " + userId + " not found.");
    }


    @DeleteMapping("/delete/{userId}")
    @Operation(description = "Delete user")
    public ResponseEntity<String> deleteUserContacts(@PathVariable Long userId) {
        Boolean isDeleted = userContactsService.deleteUserContacts(userId);
        if (isDeleted){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User with id" + userId + " successfully deleted!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id " + userId + " not found.");
    }

}
