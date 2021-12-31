package com.devcreativa.mscourse.model.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseBase {
    @Id
    private String id;
    private String name;
    private String description;
}
