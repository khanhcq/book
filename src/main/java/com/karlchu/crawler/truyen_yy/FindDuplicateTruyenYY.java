package com.karlchu.crawler.truyen_yy;


import com.karlchu.book.model.Author;
import com.karlchu.book.model.Book;
import com.karlchu.book.model.Category;
import com.karlchu.book.model.Chapter;
import com.karlchu.book.utility.WebCommonUtils;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by KhanhChu on 1/20/2019.
 */
public class FindDuplicateTruyenYY {
    private final static String DB_NAME = "stories";
    private final static String MY_SEQUENCE_COLLECTION = "sequence";
    private final static String BOOK_COLLECTION = "book";
    private final static String CHAPTER_COLLECTION = "chapter";
    private final static String TEMP_COLLECTION = "temp";


    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase db = mongoClient.getDatabase(DB_NAME);
        checkDuplciateChapter(db);
    }

    private static String checkDuplciateChapter(MongoDatabase db) {
        MongoCollection<Document> bookCollection = db.getCollection(BOOK_COLLECTION);
        MongoCollection<Document> chapterCollection = db.getCollection(CHAPTER_COLLECTION);


        FindIterable<Document> dbBooks = bookCollection.find();
        MongoCursor<Document> bookCursor = dbBooks.iterator();
        final Set<Object> bookHasDuplicate = new HashSet();
        try {
            while (bookCursor.hasNext()) {
                Map<String, Object> bookDocument = bookCursor.next();
                System.out.println(bookDocument.get("title"));
                FindIterable<Document> dbChapters = chapterCollection.find(Filters.eq("bookId", bookDocument.get("_id")));
                final Set<Object> set1 = new HashSet();
                boolean checkNumber = true;
                MongoCursor<Document> chapterCursor = dbChapters.iterator();
                try {
                    while (checkNumber && chapterCursor.hasNext()) {

                        Map<String, Object> chapterDocument = chapterCursor.next();
                        System.out.println(chapterDocument.get("number"));
                        if (!set1.add(chapterDocument.get("number"))) {
                            bookHasDuplicate.add(chapterDocument.get("bookId"));
                            checkNumber = false;
                        }
                    }
                } finally {
                    chapterCursor.close();
                }
            }
        } finally {
            bookCursor.close();
        }
        if(bookHasDuplicate.size() > 0){
            MongoCollection<Document> tempCollection = db.getCollection(TEMP_COLLECTION);
            Document document = new Document();
            document.append("bookIds", bookHasDuplicate);
            tempCollection.insertOne(document);
        }

        return "Done";
    }
}
