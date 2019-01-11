package com.karlchu.book.model;

/**
 * Created by Khanh Chu on 12/27/2018.
 */

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.sql.Timestamp;

@Document(collection = "Book")
public class Book {
    @Id
    private Long id;
    @Indexed(unique = true)
    @Field(value = "Code")
    private String code;
    @Field(value = "Title")
    private String title;
    @Field(value = "Description")
    private String description;
    @Field(value = "FileType")
    private String fileType;
    @Field(value = "Tags")
    private String tags;
    @Field(value = "Source")
    private String source;
    @Field(value = "CoverUrl")
    private String coverUrl;
    @Field(value = "Uri")
    private String uri;
    @Field(value = "AlterUri")
    private String alterUri;
//    @Field(value = "CreatedBy")
//    private Users createdBy;
    @Field(value = "CreatedDate")
    private Timestamp createdDate;
    @Field(value = "ModifiedDate")
    private Timestamp modifiedDate;
    @Field(value = "Status")
    private Integer status;
    @Field(value = "Author")
    private String author;
    @Field(value = "AuthorCode")
    private String authorCode;
    @Field(value = "Category")
    private String category;
    @Field(value = "CategoryCode")
    private String categoryCode;

    @Field(value = "View")
    private Integer view;

    public Book(String categoryCode, String authorCode) {
        this.categoryCode = categoryCode;
        this.authorCode = authorCode;
    }

    public Integer getView() {
        return view;
    }

    public void setView(Integer view) {
        this.view = view;
    }

    public Book(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public Book() {

    }

    public String getAuthorCode() {
        return authorCode;
    }

    public void setAuthorCode(String authorCode) {
        this.authorCode = authorCode;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
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

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

}
