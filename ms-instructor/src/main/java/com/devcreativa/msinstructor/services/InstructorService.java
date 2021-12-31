package com.devcreativa.msinstructor.services;

import com.devcreativa.msinstructor.model.dao.InstructorDao;
import com.devcreativa.msinstructor.model.entity.Instructor;
import com.devcreativa.msinstructor.repositories.InstructorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class InstructorService implements IOpereationServices<Instructor> {
    @Autowired
    private InstructorRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Flux<Instructor> findAll() {
        return this.repository.findAll().map(this::toInstructor);
    }

    @Override
    public Mono<Instructor> findById(String id) {
        return this.repository.findById(id).map(this::toInstructor);
    }

    @Override
    public Mono<Instructor> save(Instructor instructor) {
        return this.repository.save(this.toInstructorDao(instructor)).map(this::toInstructor);
    }

    @Override
    public Mono<Instructor> update(String id, Instructor instructor) {
        instructor.setId(id);
        return this.repository.save(this.toInstructorDao(instructor)).map(this::toInstructor);
    }

    @Override
    public Mono<Void> delete(String id) {
        return this.repository.deleteById(id);
    }

    public Instructor toInstructor(InstructorDao instructorDao) {
        return objectMapper.convertValue(instructorDao, Instructor.class);
    }

    public InstructorDao toInstructorDao(Instructor instructor) {
        return objectMapper.convertValue(instructor, InstructorDao.class);
    }
}
