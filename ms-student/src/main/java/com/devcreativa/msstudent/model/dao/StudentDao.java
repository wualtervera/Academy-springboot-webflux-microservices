package com.devcreativa.msstudent.model.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.devcreativa.msstudent.model.dto.CoursesIdDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("student")
public class StudentDao implements Serializable {
    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private List<CoursesIdDto> courses;
    private Date createdAt;
    private Date updatedAt;

}
