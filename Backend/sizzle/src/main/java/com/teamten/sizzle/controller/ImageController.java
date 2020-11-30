package com.teamten.sizzle.controller;

import com.teamten.sizzle.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController("image/")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping("/analyse")
    public void analyseImage(@RequestPart(value = "file") final MultipartFile file) {
        imageService.analyse(file);
    }

}
