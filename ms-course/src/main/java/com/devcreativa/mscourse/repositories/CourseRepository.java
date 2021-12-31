package com.devcreativa.mscourse.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.devcreativa.mscourse.model.dao.CourseDao;

public interface CourseRepository extends ReactiveCrudRepository<CourseDao, String> {
}
