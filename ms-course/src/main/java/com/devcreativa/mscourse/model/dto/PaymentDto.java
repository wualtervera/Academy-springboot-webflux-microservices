package com.devcreativa.mscourse.model.dto;

import java.time.LocalDateTime;

import com.devcreativa.mscourse.model.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {
    private String id;
    private Course course;
    private StudentDto student;
    private LocalDateTime createAt;
}
