package com.devcreativa.msinstructor.model.dao;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("instructor")
public class InstructorDao implements Serializable {
    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private String profession;
    private Date createdAt;
    private Date updatedAt;
}
