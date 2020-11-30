package com.teamten.sizzle.controller;

import com.teamten.sizzle.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ImageController {

    @Autowired
    private ImageService imageService;

    public void analyseImage() {

    }

}
