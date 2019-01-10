package com.karlchu.book.core.service;

import com.karlchu.book.core.repository.ChapterRepository;
import com.karlchu.book.core.utils.CoreUtils;
import com.karlchu.book.model.Chapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Khanh Chu on 12/27/2018.
 */

@Service
public class ChapterService {

    @Autowired
    private ChapterRepository repository;

    public List<Chapter> getChapters() {
        return repository.findAll();
    }

    public Object[] searchByPageAndSize(Long bookId, Integer page, Integer maxPageItems) {
        if(page == null) {
            page = 1;
        }
        PageRequest pageable = PageRequest.of(page - 1, maxPageItems);
        Page<Chapter> chapterPage = repository.findAll(CoreUtils.getChapterExample(bookId), pageable);
        List<Chapter> chapters = chapterPage.getContent();
        return new Object[]{String.valueOf(chapterPage.getTotalElements()), chapters, chapterPage.getTotalPages()};
    }

}
