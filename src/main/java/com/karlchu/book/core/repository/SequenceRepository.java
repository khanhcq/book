package com.karlchu.book.core.repository;

import com.karlchu.book.model.Book;
import com.karlchu.book.model.Sequence;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by KhanhChu on 1/1/2019.
 */
public interface SequenceRepository extends MongoRepository<Sequence, Long> {
    Sequence findById(String id);

}
