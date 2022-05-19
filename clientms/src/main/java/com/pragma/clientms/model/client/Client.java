package com.pragma.clientms.model.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="clients")
public class Client {
    @Id
    private Long document;
    private String firstName;
    private String lastName;
    private String city;
    private String typeOfId;
    private String age;
}
