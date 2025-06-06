package com.example.loginauth.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.loginauth.model.Product;
import com.example.loginauth.repository.ProductRepository;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/upload-product")
    public String uploadProduct(@RequestParam String pname,
                                 @RequestParam String category,
                                 @RequestParam String description,
                                 @RequestParam Double price,
                                 @RequestParam("image") MultipartFile imageFile) {
        try {
            Product product = new Product();
            product.setPname(pname);
            product.setCategory(category);
            product.setDescription(description);
            product.setPrice(price);
            product.setImage(imageFile.getBytes());

            productRepository.save(product);

            return "Product uploaded successfully!";
        } catch (IOException e) {
            return "Error saving product.";
        }
    }
}
