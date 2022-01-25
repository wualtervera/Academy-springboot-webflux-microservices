package com.devcreativa.msstudent.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoursesDto {
    private String id;
    private String name;
    private String description;
    private String price;
    private String urlImage;
    private String rating;
    private Date createdAt;
    private Date updatedAt;
}
