package com.devcreativa.msinstructor.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.devcreativa.msinstructor.model.dao.InstructorDao;

@Repository
public interface InstructorRepository extends ReactiveCrudRepository<InstructorDao, String> {
}
