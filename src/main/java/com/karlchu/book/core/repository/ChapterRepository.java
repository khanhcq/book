package com.karlchu.book.core.repository;

import com.karlchu.book.model.Chapter;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by KhanhChu on 1/1/2019.
 */
public interface ChapterRepository extends MongoRepository<Chapter, Long> {
    Chapter findByCode(String code);

    Chapter findByBookIdAndChapterNumber(Long bookId, Integer chapterNo);
}
