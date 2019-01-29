package com.karlchu.book.core.service.impl;

import com.karlchu.book.core.repository.FeedbackRepository;
import com.karlchu.book.core.service.FeedbackService;
import com.karlchu.book.dto.FeedbackDTO;
import com.karlchu.book.model.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by BVDEV75 on 1/28/2019.
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    private FeedbackRepository repository;

    @Override
    public void save(FeedbackDTO feedbackDTO) {
        Feedback feedback = new Feedback();
        feedback.setContent(feedbackDTO.getContent());
        feedback.setName(feedbackDTO.getName());
        feedback.setEmail(feedbackDTO.getEmail());
        feedback.setCreatedDate(new Date(System.currentTimeMillis()));
        this.repository.insert(feedback);

        //@TODO: emailing the feedback
    }
}
