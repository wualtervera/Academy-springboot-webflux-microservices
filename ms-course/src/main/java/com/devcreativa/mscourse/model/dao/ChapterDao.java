package com.devcreativa.mscourse.model.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("chapter")
public class ChapterDao {
    @Id
    private String id;
    private String title;
    private String content;
    private String idCourse;
    private LocalDateTime createAt;
}
