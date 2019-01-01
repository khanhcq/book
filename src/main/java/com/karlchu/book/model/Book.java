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
//    @Field(value = "BookCategory")
//    private BookCategory bookCategory;
//    @Field(value = "Author")
//    private Author author;
//    @Field(value = "CreatedBy")
//    private Users createdBy;
    @Field(value = "CreatedDate")
    private Timestamp createdDate;
    @Field(value = "ModifiedDate")
    private Timestamp modifiedDate;

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
