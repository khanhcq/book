package com.karlchu.book.core.repository.custom;

/**
 * Created by Khanh Chu on 12/27/2018.
 */

public interface BookRepositoryCustom {
    Object[] findByAuthorOrCategory(Integer page, Integer maxPageItems, String categoryCode, String authorCode, String search);
}
