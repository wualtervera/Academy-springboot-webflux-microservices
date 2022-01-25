package com.devcreativa.mscourse.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstructorShowDto {
  private String id;
  private String name;
  private String email;
  private String password;
  private String profession;
}
