package com.pragma.imagems.repository;

import com.pragma.imagems.model.image.Image;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends MongoRepository<Image, Long> {
}
