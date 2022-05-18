package com.pragma.imagems.model.image;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "imagemicro")
public class Image {
    private Long id;
    private String photo;
}
