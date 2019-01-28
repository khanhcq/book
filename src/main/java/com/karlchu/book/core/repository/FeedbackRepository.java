package com.karlchu.book.core.repository;

import com.karlchu.book.model.Chapter;
import com.karlchu.book.model.Feedback;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by KhanhChu on 1/1/2019.
 */
public interface FeedbackRepository extends MongoRepository<Feedback, Long> {

}
