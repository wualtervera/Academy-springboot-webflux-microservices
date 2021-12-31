package com.devcreativa.msenrollment.model.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Enrollment {
    private String id;
    private String idStudent;
    private String idCourse;
    private LocalDateTime createAt;
}
