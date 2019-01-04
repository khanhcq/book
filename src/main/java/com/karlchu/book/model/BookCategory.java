package com.karlchu.book.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Created by KhanhChu on 1/4/2019.
 */
@Document(collection = "BookCategory")
public class BookCategory {
    @Id
    private Long id;
    @Indexed(unique = true)
    @Field(value = "Code")
    private String code;
    @Field(value = "Title")
    private String title;
    @Field(value = "Description")
    private String description;

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
}
