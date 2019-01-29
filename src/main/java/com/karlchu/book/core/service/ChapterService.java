package com.karlchu.book.core.service;

public interface ChapterService {
    Object[] searchByPageAndSize(Long bookId, Integer page, Integer maxPageItems, boolean all);
}
