package com.karlchu.book.core.service;

import com.karlchu.book.dto.FeedbackDTO;
import com.karlchu.book.dto.UserDTO;

import java.util.Map;

public interface UserService {
    void save(UserDTO userDTO);

    Object[] searchByPageAndSize(Map<String, Object> properties, int page, int maxPageItems);
}
