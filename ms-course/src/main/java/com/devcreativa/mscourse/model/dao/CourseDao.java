package com.devcreativa.mscourse.model.dao;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.devcreativa.mscourse.model.dto.InstructorDto;
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
    private long price;
    private String urlImage;
    private int rating;
    private InstructorDto instructor;
    private Date createdAt;
    private Date updatedAt;
}
