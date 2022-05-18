package com.pragma.imagems.service;


import com.pragma.imagems.exceptions.EmptyDataException;
import com.pragma.imagems.exceptions.ErrorConstants;
import com.pragma.imagems.exceptions.NoDataFoundException;
import com.pragma.imagems.exceptions.ResourceNotFoundException;
import com.pragma.imagems.model.dto.ImageDTO;
import com.pragma.imagems.model.image.Image;
import com.pragma.imagems.model.mapper.ImageMapper;
import com.pragma.imagems.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageRepository imageRepository;

    static ImageMapper mapper = ImageMapper.singleInstance();

    @Override
    public Image createImage(ImageDTO imageDTO) {
        if (imageDTO.getId() == null || imageDTO.getPhoto().isEmpty()) {
            throw new EmptyDataException(ErrorConstants.EMPTYDATA);
        } else if (imageRepository.findById(imageDTO.getId()).isPresent()) {
            throw new EmptyDataException("Ya existe un usuario con el ID -> " + imageDTO.getId());
        }
        return imageRepository.save(mapper.dtoToDomain(imageDTO));
    }

    @Override
    public Image updateImage(Long id, ImageDTO image) {
        if(image.getId() == null || image.getPhoto().isEmpty()){
            throw new EmptyDataException(ErrorConstants.EMPTYDATA);
        } else if (id.longValue()!=image.getId().longValue()){
            throw new EmptyDataException("Los id no coinciden!!!");
        }
        Image imageToUpdate = imageRepository.findById(id).orElseThrow(()-> {
            throw new ResourceNotFoundException(ErrorConstants.NOTFOUND + id);
        });
        imageToUpdate.setPhoto(image.getPhoto());
        return imageRepository.save(imageToUpdate);
    }

    @Override
    public void deleteImage(Long id) {
        if (!imageRepository.findById(id).isPresent()){
            throw new ResourceNotFoundException(ErrorConstants.NOTFOUND + id);
        }
        imageRepository.deleteById(id);
    }

    @Override
    public List<Image> getImages() {
        if (imageRepository.findAll().isEmpty()){
            throw new NoDataFoundException(ErrorConstants.NOTDATA);
        }
        return imageRepository.findAll();
    }

    @Override
    public Image findById(Long document) {
        return imageRepository.findById(document).orElseThrow(()->{
            throw  new ResourceNotFoundException(ErrorConstants.NOTFOUND + document);
        });
    }

}
