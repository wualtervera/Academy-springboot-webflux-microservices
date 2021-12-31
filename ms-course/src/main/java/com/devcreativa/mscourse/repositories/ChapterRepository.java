package com.devcreativa.mscourse.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.devcreativa.mscourse.model.dao.ChapterDao;

@Repository
public interface ChapterRepository extends ReactiveCrudRepository<ChapterDao, String> {
}
