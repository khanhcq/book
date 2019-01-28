package com.karlchu.book.core.service;

import com.karlchu.book.core.repository.ChapterRepository;
import com.karlchu.book.core.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by BVDEV75 on 1/28/2019.
 */
@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository repository;
}
