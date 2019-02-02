package com.karlchu.book.core.repository.custom;

/**
 * Created by Khanh Chu on 12/27/2018.
 */

import com.karlchu.book.model.Book;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepositoryCustomImpl implements BookRepositoryCustom {

    @Autowired
    MongoTemplate mongoTemplate;

    public Object[] findByAuthorOrCategory(Integer page, Integer maxPageItems, String categoryCode, String authorCode, String search){
        if(page == null) {
            page = 1;
        }
        PageRequest pageable = PageRequest.of(page - 1, maxPageItems);
        Query query = new Query();
        Criteria elementMatchCriteria;
        if(categoryCode != null) {
            elementMatchCriteria = Criteria.where("categories")
                    .elemMatch(Criteria.where("code").is(categoryCode));
            query = Query.query(elementMatchCriteria).with(new Sort(Sort.Direction.ASC, "title"));
        }
        if(authorCode != null) {
            elementMatchCriteria = Criteria.where("author.code").is(authorCode);
            query = Query.query(elementMatchCriteria).with(new Sort(Sort.Direction.ASC, "title"));

        }
        if(search != null && !search.isEmpty()) {
            TextCriteria criteria = TextCriteria.forDefaultLanguage()
                    .matchingAny(search);
            query = TextQuery.queryText(criteria)
                    .sortByScore();
        }
        query.with(pageable);
//        query.fields().position("categories", 1);
        List<Book> books = mongoTemplate.find(query, Book.class);
        Long total = mongoTemplate.count(query, "book");
        Long totalPage = Math.floorDiv(total,  maxPageItems.longValue()) + 1;
        return new Object[]{total, books, totalPage };
    }
}
