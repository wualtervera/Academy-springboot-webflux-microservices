package com.devcreativa.msenrollment.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.devcreativa.msenrollment.model.dao.EnrollmentDao;
import reactor.core.publisher.Flux;

@Repository
public interface EnrollmentRepository extends ReactiveCrudRepository<EnrollmentDao, String> {

    Flux<EnrollmentDao>findByIdStudent(String idStudent);


}
