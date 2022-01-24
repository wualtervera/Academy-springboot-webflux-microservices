package com.devcreativa.mscourse.model.entity;

import com.devcreativa.mscourse.model.dto.InstructorDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    private String id;
    private String name;
    private String description;
    private InstructorDto instructor;
}
