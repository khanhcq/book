package com.karlchu.book.core.utils;

import com.karlchu.book.model.Chapter;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

/**
 * Created by KhanhChu on 1/10/2019.
 */
public class CoreUtils {
    public static Example<Chapter> getChapterExample(Long bookId){
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("BookId", match -> match.exact());
        return Example.of(new Chapter(bookId), matcher);
    }

}
