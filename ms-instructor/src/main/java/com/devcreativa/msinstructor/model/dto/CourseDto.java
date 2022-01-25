package com.devcreativa.msinstructor.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {
    private String id;
    private String name;
    private String description;
    private String price;
    private String urlImage;
    private String rating;
}
