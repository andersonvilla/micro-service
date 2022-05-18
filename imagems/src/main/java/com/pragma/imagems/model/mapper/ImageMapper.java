package com.pragma.imagems.model.mapper;


import com.pragma.imagems.model.dto.ImageDTO;
import com.pragma.imagems.model.image.Image;
import org.modelmapper.ModelMapper;

public class ImageMapper {
    private final ModelMapper modelMapper = new ModelMapper();
    private static ImageMapper instance;

    private ImageMapper() {

    }

    public static ImageMapper singleInstance() {
        if (instance == null) {
            instance = new ImageMapper();
        }
        return instance;
    }

    public Image dtoToDomain(ImageDTO imageDTO) {
        return modelMapper.map(imageDTO, Image.class);
    }

    public ImageDTO domainToDto(Image Image) {
        return modelMapper.map(Image, ImageDTO.class);
    }

}
