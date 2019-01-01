package com.karlchu.book.service;

import com.karlchu.book.domain.ChapterRepository;
import com.karlchu.book.model.Chapter;
import org.springframework.beans.factory.annotation.Autowired;
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
}
