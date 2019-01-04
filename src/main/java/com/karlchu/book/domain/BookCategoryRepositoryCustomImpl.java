package com.karlchu.book.domain;

/**
 * Created by Khanh Chu on 12/27/2018.
 */

import com.karlchu.book.model.BookCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class BookCategoryRepositoryCustomImpl implements BookRepositoryCustom {

    @Autowired
    MongoTemplate mongoTemplate;

    public long getMaxId() {
        Query query = new Query();
        query.with(new Sort(Sort.Direction.DESC, "id"));
        query.limit(1);
        BookCategory maxObject = mongoTemplate.findOne(query, BookCategory.class);
        if (maxObject == null) {
            return 0L;
        }
        return maxObject.getId();
    }
}
