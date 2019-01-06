package com.karlchu.book.core.repository.custom;

/**
 * Created by Khanh Chu on 12/27/2018.
 */

import com.karlchu.book.model.Book;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepositoryCustomImpl implements BookRepositoryCustom {

    @Autowired
    MongoTemplate mongoTemplate;

    public long getMaxId() {
        Query query = new Query();
        query.with(new Sort(Sort.Direction.DESC, "id"));
        query.limit(1);
        Book maxObject = mongoTemplate.findOne(query, Book.class);
        if (maxObject == null) {
            return 0L;
        }
        return maxObject.getId();
    }
}
