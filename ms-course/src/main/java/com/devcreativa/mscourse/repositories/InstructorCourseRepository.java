package com.devcreativa.mscourse.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.devcreativa.mscourse.model.dao.InstructorCourseDao;

@Repository
public interface InstructorCourseRepository extends ReactiveCrudRepository<InstructorCourseDao, String> {
}
