package ru.vovai.telrossofttesttask.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vovai.telrossofttesttask.model.User;
import ru.vovai.telrossofttesttask.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Get all users")
    public List<User> getAllUser() {
        return userService.findAll();
    }

    @GetMapping("/{userId}")
    @Operation(description = "Get user by id")
    public ResponseEntity<?> getUser(@PathVariable Long userId) {
        User updatedUser = userService.findById(userId);
        if (updatedUser != null){
            return ResponseEntity.ok().body(updatedUser);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id " + userId + " not found.");
    }


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "Create a new user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return  ResponseEntity.ok().body(userService.createUser(user));
    }


    @PutMapping("/update")
    @Operation(description = "Update user data")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        User updatedUser = userService.updateUser(user);
        if (updatedUser != null){
            return ResponseEntity.ok().body(updatedUser);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id " + user.getId() + " not found.");
    }



    @DeleteMapping("/delete/{id}")
    @Operation(description = "Delete user")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Boolean deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

}
