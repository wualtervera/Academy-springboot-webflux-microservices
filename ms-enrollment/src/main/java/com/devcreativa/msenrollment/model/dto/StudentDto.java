package com.devcreativa.msenrollment.model.dto;

import java.util.List;

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
  private List<CourseDto> cours;

}
