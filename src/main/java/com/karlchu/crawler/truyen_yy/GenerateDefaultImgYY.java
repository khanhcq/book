package com.karlchu.crawler.truyen_yy;

import com.karlchu.book.utility.WebCommonUtils;
import com.karlchu.crawler.utils.CrawlerUtils;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.apache.commons.io.FileUtils;
import org.bson.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GenerateDefaultImgYY {
    private final static String DB_NAME = "stories";
    private final static String BOOK_COLLECTION = "book";
    private static final String root = "E:\\";
    public static void main (String [] args) {
        String rootDir = root + "CrawledFiles";
        File fRootDir = new File(rootDir);
        String bookName;
        String fileName;
        File imgDir = new File(root + "img\\");
        if (!imgDir.exists()) {
            imgDir.mkdirs();
        }

        File img2Dir = new File(root + "img2\\");
        if (!img2Dir.exists()) {
            img2Dir.mkdirs();
        }
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase db = mongoClient.getDatabase(DB_NAME);
        MongoCollection<Document> bookCollection = db.getCollection(BOOK_COLLECTION);
        FindIterable<Document> dbBooks = bookCollection.find();
        MongoCursor<Document> bookCursor = dbBooks.iterator();
        try {
            while (bookCursor.hasNext()) {
                Map<String, Object> bookDocument = bookCursor.next();
                bookName = bookDocument.get("title").toString();
                bookName = WebCommonUtils.normalizeTitle(bookName);
                System.out.println(bookName);
                if(bookName.length() > 0){
                    try {
                        String ext = "jpg";
                        File downloadedFile = new File(root + "img\\" +bookName + "." + ext);
                        File downloadedFile2 = new File(root + "img2\\" +bookName + "." + ext);
                        if(!downloadedFile.exists() && !downloadedFile2.exists()) {
                            File imgFile = new File(root + "img2\\" +bookName + "." + ext);
                            File sampleFile = new File(root + "sampleImg\\sampleImg." + ext);
                            FileUtils.copyFile(sampleFile, imgFile);
                        }                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } finally {
            bookCursor.close();
        }
    }
}
