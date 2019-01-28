package com.karlchu.book.core.service.impl;

import com.karlchu.book.core.repository.FeedbackRepository;
import com.karlchu.book.core.service.FeedbackService;
import com.karlchu.book.dto.FeedbackDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by BVDEV75 on 1/28/2019.
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    private FeedbackRepository repository;

    @Override
    public void save(FeedbackDTO feedbackDTO) {

    }
}
