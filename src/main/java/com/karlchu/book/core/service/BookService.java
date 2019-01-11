package com.karlchu.book.core.service;

import com.karlchu.book.core.repository.BookRepository;
import com.karlchu.book.model.Book;
import com.karlchu.book.utility.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Khanh Chu on 12/27/2018.
 */

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    public List<Book> getChapters() {
        return repository.findAll();
    }

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
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("CategoryCode", match -> match.exact())
                .withMatcher("AuthorCode", match -> match.exact());
        Example example = Example.of(new Book(categoryCode, authorCode), matcher);
        PageRequest pageable = PageRequest.of(page - 1, maxPageItems);
        Page<Book> chapterPage = repository.findAll(example, pageable);
        List<Book> chapters = chapterPage.getContent();
        return new Object[]{String.valueOf(chapterPage.getTotalElements()), chapters, chapterPage.getTotalPages()};
    }
}
