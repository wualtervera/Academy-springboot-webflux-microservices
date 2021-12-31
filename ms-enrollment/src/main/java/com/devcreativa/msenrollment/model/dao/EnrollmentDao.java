package com.devcreativa.msenrollment.model.dao;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("registration")
public class EnrollmentDao implements Serializable {
    @Id
    private String id;
    private String idStudent;
    private String idCourse;
    private LocalDateTime createAt;
}
