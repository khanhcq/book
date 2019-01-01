package com.karlchu.book.domain;

import com.karlchu.book.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by KhanhChu on 1/1/2019.
 */
public interface BookRepository extends MongoRepository<Book, Long> {
    Book findByCode(String code);
}
