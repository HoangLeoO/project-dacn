package com.example.managefood.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
public class TestController {
    @GetMapping("/test")
    public String getTest() {
        return "testUploadImage";
    }

    @PostMapping("/test")
    public String postTest(@RequestParam("fileImg") MultipartFile photo) {
        Path path = Paths.get("src/main/resources/static/img/");
        System.out.println(photo.getOriginalFilename());
        try {
            InputStream inputStream = photo.getInputStream();
            Files.copy(inputStream, path.resolve(photo.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            System.out.println(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "testUploadImage";
    }
}
