package com.zypaar.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

import com.zypaar.entity.ProductImage;
import com.zypaar.repository.ProductImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductImageService {

    @Autowired
    private ProductImageRepository imageRepo;

    private final String PATH = "C:\\ABC\\";

    public ProductImage uploadImage(MultipartFile file) throws IOException {
        String fullPath = PATH + file.getOriginalFilename();
        ProductImage pImage = new ProductImage();
        pImage.setName(file.getOriginalFilename());
        pImage.setType(file.getContentType());
        pImage.setImagePath(fullPath);

        file.transferTo(new File(fullPath));
        return imageRepo.save(pImage);
    }

    public byte[] downloadImage(String fileName) throws IOException {
        Optional<ProductImage> imageObject = imageRepo.findByName(fileName);
        String fullPath = imageObject.get().getImagePath();
        return Files.readAllBytes(new File(fullPath).toPath());
    }


    public List<ProductImage> getAll() {
        return imageRepo.findAll();
    }

    public ProductImage updateUser(ProductImage user, Long id) {
        ProductImage user1=imageRepo.findById(id).get();
        user1.setId(user.getId());
        user1.setName(user.getName());
        user1.setType(user.getType());
        user1.setImagePath(user.getImagePath());
        ProductImage updateUser=imageRepo.save(user1);
        return updateUser;
    }


    public ProductImage getOneUser(Long id) {
        ProductImage user1=imageRepo.findById(id).get();
        return user1;
    }

    public void deleteUser(Long id) {
        ProductImage user1=imageRepo.findById(id).get();
        imageRepo.delete(user1);
    }
}
