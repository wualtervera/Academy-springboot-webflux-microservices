package com.devcreativa.mscourse.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import com.devcreativa.mscourse.model.dao.CourseDao;
import reactor.core.publisher.Flux;

public interface CourseRepository extends ReactiveCrudRepository<CourseDao, String> {
  Flux<CourseDao> findByInstructorId(String id);
}
