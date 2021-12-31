package com.devcreativa.msstudent.model.dao;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("student")
public class StudentDao implements Serializable {
    @Id
    private String id;
    private String nombre;
    private int edad;
}
