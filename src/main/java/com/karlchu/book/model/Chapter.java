package com.karlchu.book.model;

/**
 * Created by Khanh Chu on 12/27/2018.
 */

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "Chapter")
public class Chapter {

    @Id
    private Long id;

    @Indexed(unique = true)
    @Field(value = "Code")
    private String code;

    @Field(value = "ChapterNumber")
    private Integer chapterNumber;

    @Field(value = "ChapterTitle")
    private String chapterTitle;

    @Field(value = "Content")
    private String content;

    @Field(value = "BookId")
    private Long bookId;

    public Chapter() {
    }

    public Chapter(Long bookId) {
        this.bookId = bookId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

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

    public Integer getChapterNumber() {
        return chapterNumber;
    }

    public void setChapterNumber(Integer chapterNumber) {
        this.chapterNumber = chapterNumber;
    }

    public String getChapterTitle() {
        return chapterTitle;
    }

    public void setChapterTitle(String chapterTitle) {
        this.chapterTitle = chapterTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
