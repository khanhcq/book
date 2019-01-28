package com.karlchu.book.core.service.impl;

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

    public Object[] searchByPageAndSize(Long bookId, Integer page, Integer maxPageItems, boolean all) {
        if(page == null) {
            page = 1;
        }
        PageRequest pageable = PageRequest.of(page - 1, maxPageItems);
        Page<Chapter> chapterPage;
        List<Chapter> chapters;
        String total;
        int totalPage;
        if(all){
            chapters = repository.findAll(CoreUtils.getChapterExample(bookId));
            total = String.valueOf(chapters.size());
            totalPage = 1;
        } else{
            chapterPage = repository.findAll(CoreUtils.getChapterExample(bookId), pageable);
            chapters = chapterPage.getContent();
            total = String.valueOf(chapterPage.getTotalElements());
            totalPage = chapterPage.getTotalPages();
        }
        return new Object[]{total, chapters, totalPage};
    }

}
