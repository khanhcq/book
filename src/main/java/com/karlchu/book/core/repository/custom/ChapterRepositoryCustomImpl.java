package com.karlchu.book.core.repository.custom;

/**
 * Created by Khanh Chu on 12/27/2018.
 */

import com.karlchu.book.model.Chapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ChapterRepositoryCustomImpl implements ChapterRepositoryCustom {

    @Autowired
    MongoTemplate mongoTemplate;

    public long getMaxId() {
        Query query = new Query();
        query.with(new Sort(Sort.Direction.DESC, "id"));
        query.limit(1);
        Chapter maxObject = mongoTemplate.findOne(query, Chapter.class);
        if (maxObject == null) {
            return 0L;
        }
        return maxObject.getId();
    }
}
