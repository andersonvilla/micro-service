package com.pragma.imagems.service;

import com.pragma.imagems.model.dto.ImageDTO;
import com.pragma.imagems.model.image.Image;

import java.util.List;

public interface ImageService {
    Image createImage(ImageDTO Image);
    Image updateImage(Long document, ImageDTO Image);
    void deleteImage(Long document);
    List<Image> getImages();
    Image findById(Long document);

}
