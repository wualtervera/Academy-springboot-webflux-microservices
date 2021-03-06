package com.devcreativa.msstudent.model.entity;

import java.util.Date;
import java.util.List;

import com.devcreativa.msstudent.model.dto.CoursesDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private String id;
    private String name;
    private String email;
    private String password;
    private List<CoursesDto> courses;
    private Date createdAt;
    private Date updatedAt;
}
