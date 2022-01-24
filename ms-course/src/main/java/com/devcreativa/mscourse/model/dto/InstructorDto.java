package com.devcreativa.msinstructor.model.entity;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Instructor {
    private String id;
    private String nombre;
    private int edad;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public void createdDate(){
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

}
