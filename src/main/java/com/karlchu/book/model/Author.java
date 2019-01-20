package com.karlchu.book.model;

/**
 * Created by KhanhChu on 1/20/2019.
 */
public class Author {
    private String name;
    private String code;

    public Author() {
    }

    public Author(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public Author(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
