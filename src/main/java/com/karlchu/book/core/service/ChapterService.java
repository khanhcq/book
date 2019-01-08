package com.karlchu.book.core.service;

import com.karlchu.book.core.repository.ChapterRepository;
import com.karlchu.book.model.Chapter;
import com.karlchu.book.utility.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    public Object[] searchByProperties(Map<String, Object> properties, String sortExpression, String sortDirection, int page, int maxPageItems) {
        PageRequest pageable = PageRequest.of(page,
                maxPageItems,
                Constants.SORT_ASC.equals(sortDirection)
                        ? Sort.by(sortExpression).ascending()
                        : Sort.by("sortExpression").descending());
        Page<Chapter> chapterPage = repository.findAll(pageable);
        List<Chapter> chapters = chapterPage.getContent();
        return new Object[]{String.valueOf(chapterPage.getTotalElements()), chapters, chapterPage.getTotalPages()};
    }
}
