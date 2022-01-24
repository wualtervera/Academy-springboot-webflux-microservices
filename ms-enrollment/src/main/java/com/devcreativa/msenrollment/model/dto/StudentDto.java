package com.devcreativa.mscourse.model.dto;

import java.util.List;

import com.devcreativa.mscourse.model.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
  private String id;
  private String nombre;
  private int edad;
  private List<Course> courses;

}
