package com.karlchu.book.core.service.impl;

import com.karlchu.book.core.repository.BookRepository;
import com.karlchu.book.core.service.BookService;
import com.karlchu.book.model.Author;
import com.karlchu.book.model.Book;
import com.karlchu.book.model.Category;
import com.karlchu.book.model.Rate;
import com.karlchu.book.utility.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by Khanh Chu on 12/27/2018.
 */

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository repository;

    public Object[] searchByProperties(Map<String, Object> properties, String sortExpression, String sortDirection, int page, int maxPageItems) {
        PageRequest pageable = PageRequest.of(page,
                maxPageItems,
                Constants.SORT_ASC.equals(sortDirection)
                        ? Sort.by(sortExpression).ascending()
                        : Sort.by("sortExpression").descending());
        Page<Book> bookPage = repository.findAll(pageable);
        List<Book> books = bookPage.getContent();
        return new Object[]{String.valueOf(bookPage.getTotalElements()), books, bookPage.getTotalPages()};
    }

    public Object[] searchByPageAndSize(Integer page, Integer maxPageItems, String categoryCode, String authorCode) {
        if(page == null) {
            page = 1;
        }
        ExampleMatcher matcher = ExampleMatcher.matching();
        Book bookSample = new Book();
        if(StringUtils.isNotEmpty(authorCode)){
            matcher = matcher.withMatcher("author.code", match -> match.exact());
            bookSample.setAuthor(new Author(authorCode));
        }
        if(StringUtils.isNotEmpty(categoryCode)){
            matcher = matcher.withMatcher("categories.code", match -> match.exact()); //@Todo: need update this.
            ArrayList<Category> categories = new ArrayList<>();
            categories.add(new Category(categoryCode));
            bookSample.setCategories(categories);
        }

        Example example = Example.of(bookSample, matcher);
        PageRequest pageable = PageRequest.of(page - 1, maxPageItems);
        Page<Book> chapterPage = repository.findAll(example, pageable);
        List<Book> chapters = chapterPage.getContent();
        return new Object[]{String.valueOf(chapterPage.getTotalElements()), chapters, chapterPage.getTotalPages()};
    }

    @Override
    public Object[] updateRating(Long bookId, Integer point) {
        Optional bookOpt = repository.findById(bookId);
        if (bookOpt.isPresent()) {
            Book book = (Book) bookOpt.get();
            Rate rate = book.getRate();
            if(rate == null) {
                rate = new Rate();
            }
            Integer no = rate.getNo() != null ?  rate.getNo() + 1 : 2;
            rate.setNo(no);
            Integer newPoint = rate.getPoint() != null ? (rate.getPoint() * (no - 1) + point) / no : (60 + point) / no;
            rate.setPoint(newPoint);
            book.setRate(rate);
            repository.save(book);
            return new Object[] {no, newPoint};
        }
        return null;
    }
}
