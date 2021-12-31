package com.devcreativa.mscourse.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcreativa.mscourse.model.dao.ChapterDao;
import com.devcreativa.mscourse.model.entity.Chapter;
import com.devcreativa.mscourse.repositories.ChapterRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ChapterService implements IOpereationServices<Chapter> {

    @Autowired
    private ChapterRepository repository;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public Flux<Chapter> findAll() {
        return this.repository.findAll().map(this::toChapter);
    }

    @Override
    public Mono<Chapter> findById(String id) {
        return this.repository.findById(id).map(this::toChapter);
    }

    @Override
    public Mono<Chapter> save(Chapter chapter) {
        chapter.setCreateAt(LocalDateTime.now());
        return this.repository.save(this.toChapterDao(chapter)).map(this::toChapter);
    }

    @Override
    public Mono<Chapter> update(String id, Chapter chapter) {
        chapter.setId(id);
        chapter.setCreateAt(LocalDateTime.now());
        return this.repository.save(this.toChapterDao(chapter)).map(this::toChapter);
    }

    @Override
    public Mono<Void> delete(String id) {
        return this.repository.deleteById(id);
    }

    public Chapter toChapter(ChapterDao chapterDao) {
        return this.mapper.convertValue(chapterDao, Chapter.class);
    }

    public ChapterDao toChapterDao(Chapter chapter) {
        return this.mapper.convertValue(chapter, ChapterDao.class);
    }


}
