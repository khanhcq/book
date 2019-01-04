package com.karlchu.book.domain;

import com.karlchu.book.model.BookCategory;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by KhanhChu on 1/1/2019.
 */
public interface BookCategoryRepository extends MongoRepository<BookCategory, Long> {
    BookCategory findByCode(String code);
}
