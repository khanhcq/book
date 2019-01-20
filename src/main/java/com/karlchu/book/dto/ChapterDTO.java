package com.karlchu.book.dto;

import java.io.Serializable;

/**
 * Created by KhanhChu on 1/1/2019.
 */
public class ChapterDTO implements Serializable {
    private static final long serialVersionUID = -6271500581317762598L;
    private Long id;
    private String code;
    private String number;
    private String title;
    private String content;
    private BookDTO book;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BookDTO getBook() {
        return book;
    }

    public void setBook(BookDTO book) {
        this.book = book;
    }
}
