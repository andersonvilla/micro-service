package com.pragma.imagems.controller;

import com.pragma.imagems.model.dto.ImageDTO;
import com.pragma.imagems.model.image.Image;
import com.pragma.imagems.service.ImageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/imagems")
public class ImageController {
    @Autowired
    private ImageServiceImpl imageService;


    @GetMapping
    ResponseEntity<List<Image>> listImage(){
        return ResponseEntity.ok(imageService.getImages());
    }

    @PostMapping
    ResponseEntity<Image> createImage(@RequestBody ImageDTO imageDTO){
        return new  ResponseEntity<>(imageService.createImage(imageDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{document}")
    ResponseEntity<Image> getImageById(@PathVariable ("document") Long document){
        return ResponseEntity.ok(imageService.findById(document));
    }

    @DeleteMapping("/{document}")
    ResponseEntity<Void> deleteImage(@PathVariable ("document") Long document){
        imageService.deleteImage(document);
        return ResponseEntity.status(204).build();
    }

    @PutMapping("/{document}")
    ResponseEntity<Image> updateImage(@PathVariable ("document") Long document, @RequestBody ImageDTO imageDTO){
        imageService.updateImage(document, imageDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
