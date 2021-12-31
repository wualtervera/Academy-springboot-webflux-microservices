package com.devcreativa.mscourse.model.dao;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("course")
public class CourseDao implements Serializable {
    @Id
    private String id;
    private String name;
    private String description;
}
