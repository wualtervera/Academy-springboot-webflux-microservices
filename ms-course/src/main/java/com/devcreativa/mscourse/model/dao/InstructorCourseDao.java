package com.devcreativa.mscourse.model.dao;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("instructorCourse")
public class InstructorCourseDao {
    @Id
    private String id;
    private String idInstructor;
    private String idCourse;
    private LocalDateTime createAt;
}
