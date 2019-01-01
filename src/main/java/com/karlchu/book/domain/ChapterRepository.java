package com.karlchu.book.domain;

import com.karlchu.book.model.Book;
import com.karlchu.book.model.Chapter;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by KhanhChu on 1/1/2019.
 */
public interface ChapterRepository extends MongoRepository<Chapter, Long> {
    Chapter findByCode(String code);
}
