package com.devcreativa.mscourse.model.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstructorCourse {
    private String id;
    private String idInstructor;
    private String idCourse;
    private LocalDateTime createAt;
}
