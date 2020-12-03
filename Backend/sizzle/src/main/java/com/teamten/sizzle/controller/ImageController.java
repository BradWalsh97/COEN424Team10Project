package com.teamten.sizzle.controller;

import com.teamten.sizzle.model.Tag;
import com.teamten.sizzle.service.ImageService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping("/analyseFile")
    public List<Tag> analyseImage(@RequestBody final File file){
        return imageService.analyse(file);
    }

    @PostMapping("/analyseByte")
    public List<Tag> analyseImage(@RequestBody final byte[] file) throws IOException {
        File tmpByteFile = new File("tmpByteFile");
        FileUtils.writeByteArrayToFile(tmpByteFile, file);
        return imageService.analyse(tmpByteFile);
    }
    @PostMapping("/analyseMultiFile")
    public List<Tag> analyseImage(@RequestPart("file") final MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        File tmpFile = new File("image.jpeg");
        FileUtils.writeByteArrayToFile(tmpFile, bytes);
        return imageService.analyse(tmpFile);
    }


}
