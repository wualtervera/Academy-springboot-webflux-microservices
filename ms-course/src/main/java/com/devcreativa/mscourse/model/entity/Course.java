package com.devcreativa.mscourse.model.entity;

import java.util.Date;

import com.devcreativa.mscourse.model.dto.InstructorDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Course {
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
