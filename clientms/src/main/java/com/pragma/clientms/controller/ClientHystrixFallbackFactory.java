package com.pragma.clientms.controller;

import com.pragma.clientms.feign.ImageFeignClient;
import com.pragma.clientms.model.image.Image;

public class ClientHystrixFallbackFactory implements ImageFeignClient {
    @Override
    public Image save(Image image) {
        return null;
    }

    @Override
    public Image getImageById(Long document) {
        Image image = Image.builder()
                .id(0L)
                .photo("none").build();
        return image;
    }

    @Override
    public void deleteImage(Long document) {

    }

    @Override
    public Image updateImage(Long document, Image image) {
        return null;
    }
}
