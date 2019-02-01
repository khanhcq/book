package com.karlchu.book.utility;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.redfin.sitemapgenerator.ChangeFreq;
import com.redfin.sitemapgenerator.WebSitemapGenerator;
import com.redfin.sitemapgenerator.WebSitemapUrl;
import org.bson.Document;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

/**
 * Created by BVDEV75 on 2/1/2019.
 */
public class GenSitemap {
    private static final String BASE_URL = "https://truyen.co";
    private final static String DB_NAME = "stories";
    private final static String BOOK_COLLECTION = "book";
    public static void main (String [] args) throws IOException {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase db = mongoClient.getDatabase(DB_NAME);
        createSitemap(db);
    }

    public static void createSitemap(MongoDatabase db) throws IOException {
        MongoCollection<Document> bookCollection = db.getCollection(BOOK_COLLECTION);
        WebSitemapGenerator sitemapGenerator = WebSitemapGenerator
                .builder(BASE_URL, new File("E:\\"))
                .build();
        FindIterable<Document> dbBooks = bookCollection.find();
        MongoCursor<Document> bookCursor = dbBooks.iterator();
        StringBuilder builder;
        WebSitemapUrl sitemapUrl;
        try {
            while (bookCursor.hasNext()) {
                builder = new StringBuilder(BASE_URL);
                Map<String, Object> bookDocument = bookCursor.next();
                builder.append("/book?id=")
                        .append(bookDocument.get("_id"))
                        .append("&all=true");
                sitemapUrl = new WebSitemapUrl.Options(builder.toString())
                        .lastMod(new Date()).priority(1.0)
                        .changeFreq(ChangeFreq.WEEKLY).build();
                sitemapGenerator.addUrl(sitemapUrl);

            }
        } finally {
            bookCursor.close();
        }
        sitemapGenerator.write();
    }
}
