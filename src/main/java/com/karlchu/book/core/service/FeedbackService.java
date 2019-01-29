package com.karlchu.book.core.service;

import com.karlchu.book.dto.FeedbackDTO;

import java.util.Map;

public interface FeedbackService {
    void save(FeedbackDTO feedbackDTO);

    Object[] searchByPageAndSize(Map<String, Object> properties, int page, int maxPageItems);
}
