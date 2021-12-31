package com.devcreativa.mscourse.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcreativa.mscourse.model.dao.InstructorCourseDao;
import com.devcreativa.mscourse.model.entity.InstructorCourse;
import com.devcreativa.mscourse.repositories.InstructorCourseRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class InstructorCourseService implements IOpereationServices<InstructorCourse> {

    @Autowired
    private InstructorCourseRepository repository;

    @Autowired
    private ObjectMapper mapper;


    @Override
    public Flux<InstructorCourse> findAll() {
        return repository.findAll().map(this::toInstructorCourse);
    }

    @Override
    public Mono<InstructorCourse> findById(String id) {
        return repository.findById(id).map(this::toInstructorCourse);
    }

    @Override
    public Mono<InstructorCourse> save(InstructorCourse instructorCourse) {
        instructorCourse.setCreateAt(LocalDateTime.now());
        return repository.save(this.toInstructorCourseDao(instructorCourse)).map(this::toInstructorCourse);
    }

    @Override
    public Mono<InstructorCourse> update(String id, InstructorCourse instructorCourse) {
        instructorCourse.setId(id);
        instructorCourse.setCreateAt(LocalDateTime.now());
        return repository.save(this.toInstructorCourseDao(instructorCourse)).map(this::toInstructorCourse);
    }

    @Override
    public Mono<Void> delete(String id) {
        return this.repository.deleteById(id);
    }


    public InstructorCourse toInstructorCourse(InstructorCourseDao instructorCourseDao) {
        return mapper.convertValue(instructorCourseDao, InstructorCourse.class);
    }

    public InstructorCourseDao toInstructorCourseDao(InstructorCourse instructorCourse) {
        return mapper.convertValue(instructorCourse, InstructorCourseDao.class);
    }
}
