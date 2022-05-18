package com.pragma.clientms.feign;

import com.pragma.clientms.model.image.Image;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "imagem-service", url = "http://localhost:8002", path = "/imagems")
public interface ImageFeignClient {

    @PostMapping()
    Image save(@RequestBody Image image);

    @GetMapping("/{document}")
    Image getImageById(@PathVariable("document") Long document);

    @DeleteMapping("/{document}")
    void deleteImage(@PathVariable("document") Long document);

    @PutMapping("/{document}")
    Image updateImage(@PathVariable("document") Long document, Image image);

}
