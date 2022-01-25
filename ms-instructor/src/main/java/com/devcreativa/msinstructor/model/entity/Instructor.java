package com.devcreativa.msinstructor.model.entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.devcreativa.msinstructor.model.dto.CourseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Instructor {
    private String id;
    private String name;
    private String email;
    private String password;
    private String profession;
    List<CourseDto> courses;
    private Date createdAt;
    private Date updatedAt;
}
