package com.zypaar.controller;

import com.zypaar.entity.ProductImage;
import com.zypaar.services.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductImageController {

    @Autowired
    private ProductImageService productImageService;

    //http://localhost:8081/api/upload
    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping("/upload")
    public void uploadImage(@RequestParam("productImage") MultipartFile file) throws IOException {
        productImageService.uploadImage(file);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable String fileName) throws IOException {
        byte[] image = productImageService.downloadImage(fileName);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image);
    }

    //http://localhost:8080/api/getAll
    @GetMapping("/getAll")
    public List<ProductImage> getAllUserController(){
        List<ProductImage> user1=productImageService.getAll();
        return user1;
    }

    //http://localhost:8080/api/user/id
    @GetMapping("/user/{id}")
    public ResponseEntity<ProductImage> getUserById(@PathVariable("id") Long id){
        ProductImage user=productImageService.getOneUser(id);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    //http://localhost:8080/api/update/id
    @PutMapping("/update/{id}")
    public ResponseEntity<ProductImage> updateAllUser( @RequestBody ProductImage user,@PathVariable("id") Long id){
        ProductImage user1=productImageService.updateUser(user,id);
        return new ResponseEntity<>(user1,HttpStatus.ACCEPTED);
    }

    //http://localhost:8080/api/delete/id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOneUser(@PathVariable("id") Long id){
        productImageService.deleteUser(id);
        return new ResponseEntity<>("User delete successfully",HttpStatus.OK);
    }
}
