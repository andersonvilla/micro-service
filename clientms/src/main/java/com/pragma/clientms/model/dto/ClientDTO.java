package com.pragma.clientms.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {
    private Long document;
    private String firstName;
    private String lastName;
    private String city;
    private String typeOfId;
    private String age;
    private String image;
}
