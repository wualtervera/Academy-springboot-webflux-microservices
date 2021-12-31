package com.devcreativa.msstudent.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.devcreativa.msstudent.model.dao.StudentDao;


@Repository
public interface StudentRepository extends ReactiveCrudRepository<StudentDao, String> {


}
