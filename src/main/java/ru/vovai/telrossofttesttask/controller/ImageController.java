package ru.vovai.telrossofttesttask.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.vovai.telrossofttesttask.model.Image;
import ru.vovai.telrossofttesttask.service.ImageService;

import java.io.IOException;

@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @GetMapping("/{userId}")
    @Operation(description = "Get user photo")
    public ResponseEntity<byte[]> getImageByUserId(@PathVariable Long userId){
        Image image = imageService.getImage(userId);
        return ResponseEntity.ok().contentType(MediaType.valueOf(image.getContentType())).body(image.getImageData());
    }


    @PostMapping("/create/{userId}")
    @Operation(description = "Upload user photo")
    public ResponseEntity<String>  uploadImage(@PathVariable Long userId, @RequestParam("image")MultipartFile file) throws IOException {
        String response = imageService.uploadImage(file, userId);
        if (response != null){
            return ResponseEntity.ok().body(response);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id = " + userId + " is not found.");
    }

    @PutMapping("/update/{userId}")
    @Operation(description = "Update user photo")
    public ResponseEntity<String> updateImage(@PathVariable Long userId, @RequestParam("image")MultipartFile file) throws IOException {
        String response = imageService.updateImage(file, userId);
        if (response != null){
            return ResponseEntity.ok().body(response);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id = " + userId + " is not found, or this user don't have photo to Update.");
    }

    @DeleteMapping("/delete/{userId}")
    @Operation(description = "Delete user photo")
    public ResponseEntity<String> deleteImage(@PathVariable Long userId){
        String response = imageService.deleteImage(userId);
        if (response != null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id = " + userId + " is not found, or this user don't have photo to delete.");
    }
}
