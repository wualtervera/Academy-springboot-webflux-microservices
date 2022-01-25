package com.devcreativa.mscourse.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseShowDto {
  private String id;
  private String name;
  private String description;
  private long price;
  private String urlImage;
  private int rating;
  private InstructorShowDto instructor;
  private Date createdAt;
  private Date updatedAt;
}
