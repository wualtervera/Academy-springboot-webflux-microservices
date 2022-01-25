package com.devcreativa.msenrollment.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcreativa.msenrollment.model.dao.EnrollmentDao;
import com.devcreativa.msenrollment.model.entity.Enrollment;
import com.devcreativa.msenrollment.repositories.EnrollmentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EnrollmentService implements IOpereationServices<Enrollment> {

    @Autowired
    private EnrollmentRepository repository;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public Flux<Enrollment> findAll() {
        return this.repository.findAll().map(this::toRegistration);
    }

    @Override
    public Mono<Enrollment> findById(String id) {
        return this.repository.findById(id).map(this::toRegistration);
    }

    @Override
    public Mono<Enrollment> save(Enrollment enrollment) {
        enrollment.setCreateAt(LocalDateTime.now());
        return this.repository.save(this.toRegistrationDao(enrollment)).map(this::toRegistration);
    }

    @Override
    public Mono<Enrollment> update(String id, Enrollment enrollment) {
        enrollment.setId(id);
        enrollment.setCreateAt(LocalDateTime.now());
        return this.repository.save(this.toRegistrationDao(enrollment)).map(this::toRegistration);
    }

    public Mono<Enrollment> patch(Enrollment enrollment) {
        enrollment.setCreateAt(LocalDateTime.now());
        return this.repository.save(this.toRegistrationDao(enrollment)).map(this::toRegistration);
    }

    @Override
    public Mono<Void> delete(String id) {
        return this.repository.deleteById(id);
    }

    public Flux<Enrollment> findByIdStudent(String idStudent) {
        return this.repository.findByIdStudent(idStudent).map(this::toRegistration);


    }


    public Enrollment toRegistration(EnrollmentDao enrollmentDao) {
        return this.mapper.convertValue(enrollmentDao, Enrollment.class);
    }

    public EnrollmentDao toRegistrationDao(Enrollment enrollment) {
        return this.mapper.convertValue(enrollment, EnrollmentDao.class);
    }

}
