package com.devcreativa.msenrollment.model.dto;

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
    private InstructorDto instructor;
}
