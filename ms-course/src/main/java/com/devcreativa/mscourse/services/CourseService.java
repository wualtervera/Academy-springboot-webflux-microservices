package com.devcreativa.mscourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcreativa.mscourse.model.dao.CourseDao;
import com.devcreativa.mscourse.model.entity.Course;
import com.devcreativa.mscourse.repositories.CourseRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CourseService implements IOpereationServices<Course> {

    @Autowired
    private CourseRepository repository;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public Flux<Course> findAll() {
        return repository.findAll().map(this::toCourse);
    }

    @Override
    public Mono<Course> findById(String id) {
        return this.repository.findById(id).map(this::toCourse);
    }

    @Override
    public Mono<Course> save(Course course) {
        return this.repository.save(this.toCourseDao(course)).map(this::toCourse);
    }

    @Override
    public Mono<Course> update(String id, Course course) {
        course.setId(id);
        return this.repository.save(this.toCourseDao(course)).map(this::toCourse);
    }

    @Override
    public Mono<Void> delete(String id) {
        return this.repository.deleteById(id);
    }

    public Course toCourse(CourseDao courseDao) {
        return mapper.convertValue(courseDao, Course.class);
    }

    public CourseDao toCourseDao(Course course) {
        return mapper.convertValue(course, CourseDao.class);
    }
}
