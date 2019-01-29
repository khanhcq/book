package com.karlchu.book.core.service;


import java.util.Map;

public interface BookService {
    Object[] searchByProperties(Map<String, Object> properties, String sortExpression, String sortDirection, int page, int maxPageItems);

    Object[] searchByPageAndSize(Integer page, Integer maxPageItems, String categoryCode, String authorCode);

}
