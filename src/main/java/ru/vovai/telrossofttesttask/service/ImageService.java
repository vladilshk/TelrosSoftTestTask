package ru.vovai.telrossofttesttask.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.vovai.telrossofttesttask.model.Image;
import ru.vovai.telrossofttesttask.model.User;
import ru.vovai.telrossofttesttask.repository.ImageRepository;
import ru.vovai.telrossofttesttask.util.ImageUtils;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final UserService userService;

    public String uploadImage(MultipartFile file, Long userId) throws IOException {
        User user = userService.findById(userId);
        if (user == null) {
            return null;
        }
        if (user.getImage() != null){
            return null;
        }
        imageRepository.save(convertFileToImage(file, user));
        return "Photo of user with id = " + userId + " uploaded successfully.";
    }

    public Image getImage(Long userId) {
        User user = userService.findById(userId);
        if (user == null) {
            return null;
        }
        Image dbImage = imageRepository.findByUser(user).orElse(null);
        if (dbImage == null){
            return null;
        }
        dbImage.setImageData(ImageUtils.decompressImage(dbImage.getImageData()));
        return dbImage;
    }

    @Transactional
    public String updateImage(MultipartFile file, Long userId) throws IOException {
        User user = userService.findById(userId);
        if (user == null) {
            return null;
        }
        Image image = user.getImage();
        if (image != null){
            imageRepository.deleteById(user.getImage().getId());
            imageRepository.save(convertFileToImage(file, user));
            return "Image updated successfully";
        }
        return null;
    }

    public Boolean deleteImage(Long userId){
        User user = userService.findById(userId);
        if (user == null){
            return false;
        }
        Optional<Image> image = imageRepository.findByUser(user);
        if (image.isEmpty()){
            return null;
        }
        imageRepository.deleteById(image.get().getId());
        return true;
    }

    private Image convertFileToImage(MultipartFile file, User user) throws IOException {
        return Image.builder()
                .originalFileName(file.getOriginalFilename())
                .contentType(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes()))
                .user(user)
                .build();
    }

}
