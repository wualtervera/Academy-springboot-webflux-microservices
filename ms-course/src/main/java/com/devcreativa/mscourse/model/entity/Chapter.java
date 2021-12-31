package com.devcreativa.mscourse.model.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Chapter {
    private String id;
    private String title;
    private String content;
    private String idCourse;
    private LocalDateTime createAt;
}
