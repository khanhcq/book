package com.karlchu.book.model;

/**
 * Created by Khanh Chu on 12/27/2018.
 */

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.TextScore;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "book")
public class Book {
    @Id
    private Long id;
    @Indexed(unique = true)
    private String code;
    @TextIndexed(weight=3)
    private String title;
    private String description;
    private String fileType;
    private String tags;
    private String source;
    private String coverUrl;
    private String uri;
    private String alterUri;
    private Date createdDate;
    private Date modifiedDate;
    private Integer status;
    @TextIndexed(weight=2)
    private Author author;
    @TextIndexed(weight=1)
    private List<Category> categories;
    private List<String> categoryCodes;
    private Integer view;
    @TextScore
    private Float score;

    public Book(List<Category> categories, Author author) {
        this.categories = categories;
        this.author = author;
    }

    public Book(String categoryCode, String authorCode) {
        ArrayList<Category> categories = new ArrayList<>();
        Category category = new Category(categoryCode);
        categories.add(category);
        this.categories = categories;

        Author author = new Author(authorCode);
        this.author = author;
    }

    public Book(String authorCode) {
        Author author = new Author(authorCode);
        this.author = author;
    }

    public Integer getView() {
        return view;
    }

    public void setView(Integer view) {
        this.view = view;
    }

    public Book() {

    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<String> getCategoryCodes() {
        return categoryCodes;
    }

    public void setCategoryCodes(List<String> categoryCodes) {
        this.categoryCodes = categoryCodes;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getAlterUri() {
        return alterUri;
    }

    public void setAlterUri(String alterUri) {
        this.alterUri = alterUri;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

}
