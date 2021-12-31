package com.devcreativa.msinstructor.model.dao;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("instructor")
public class InstructorDao implements Serializable {
    @Id
    private String id;
    private String nombre;
    private int edad;
}
