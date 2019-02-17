package com.karlchu.crawler.truyen_yy;


import com.karlchu.book.model.Author;
import com.karlchu.book.model.Book;
import com.karlchu.book.model.Category;
import com.karlchu.book.model.Chapter;
import com.karlchu.book.utility.WebCommonUtils;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
public class RatingTruyenYY {
    private final static String DB_NAME = "stories";
    private final static String BOOK_COLLECTION = "book";

    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase db = mongoClient.getDatabase(DB_NAME);
        String rootDir = "E:\\CrawledFiles";
        File fRootDir = new File(rootDir);
        String bookName;
        String fileName;
        int i = 0;
        for (File file : fRootDir.listFiles()) {
            fileName = file.getName();
            if (file.isFile() && fileName.endsWith(".done")) {
                bookName = fileName.replaceAll("\\.done", "");
                if (bookName.length() > 0) {
                    System.out.println(i + " = " + bookName);
                    testInsert(db, bookName, rootDir + "\\" + bookName);
                    i++;
                }
            }
        }
    }

    private static String testInsert(MongoDatabase db, String bookName, String bookDir) {
        try {
            String introChapDir = bookDir + "\\" + bookName + ".html";
            StringBuilder stringBuilder = new StringBuilder();
            String line;

            BufferedReader reader = new BufferedReader(new FileReader(introChapDir));
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }


            org.jsoup.nodes.Document document = Jsoup.parse(stringBuilder.toString());
            Element bookNameE = document.select(".novel-info .info .name").first();
            if (bookNameE == null) {
                return "";
            }
            String bookTitle = bookNameE.text().trim();
            Element authorE = document.select(".novel-info .info .author").first();
            String sAuthor = authorE.text().trim();
            Author author = new Author(sAuthor, WebCommonUtils.normalizeTitle(sAuthor));
            Element cateE = document.select(".novel-info .info .tag-list").first();
            List<Category> categories = new ArrayList<>();
            String tag;
            for (Element e : cateE.select(".tag")) {
                tag = e.text().trim();
                Category category = new Category(tag, WebCommonUtils.normalizeTitle(tag));
                categories.add(category);
            }

            Elements ratingData = document.select(".novel-info .info .numbers").first().select("li");
            Element viewE = ratingData.get(2);
            Integer noView = Integer.valueOf(viewE.select("span").first().attr("title").trim().replace(",", ""));

            Element likeE = ratingData.get(3);
            Integer noLike = Integer.valueOf(likeE.select("span").first().attr("title").trim().replace(",", ""));

            Element voteE = ratingData.get(4);
            Integer noVote = Integer.valueOf(voteE.select("a span").first().attr("title").trim().replace(",", ""));

            Integer noRate = 0;
            Integer averagePoint = 0;
            Element ratingE = document.select(".novel-info .rating a").first();
            if(ratingE != null){
                Element ratingNoE = ratingE.select("span").first();
                String rating = ratingE.text();
                String ratingNo = ratingNoE.text();
                rating = rating.replace(ratingNo, "").trim();
                ratingNo = ratingNo.replace("Đề Cử", "").trim();
                noRate = Integer.valueOf(ratingNo);
                averagePoint = Integer.valueOf(rating);
                if (averagePoint < 21) {
                    averagePoint = 20;
                } else if (averagePoint < 41) {
                    averagePoint = 40;
                } else if (averagePoint < 61) {
                    averagePoint = 60;
                } else if (averagePoint < 81) {
                    averagePoint = 80;
                } else if (averagePoint < 101) {
                    averagePoint = 100;
                }
            }

            MongoCollection<Document> bookCollection = db.getCollection(BOOK_COLLECTION);
            String bookCode = computeCode(author.getName(), bookTitle);
            Document dBook = new Document();
            dBook.append("view", noView);
            dBook.append("like", noLike);
            dBook.append("vote", noVote);
            if(noRate > 0) {
                Document rateDoc = new Document();
                rateDoc.append("no", noRate);
                rateDoc.append("point", averagePoint);
                dBook.append("rate", rateDoc);
            }
            bookCollection.updateOne(Filters.eq("code", bookCode), new Document("$set", dBook));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Done";
    }

    private static String computeCode(String bookTitle, String chapTitle) {
        String bookCode = WebCommonUtils.getFirstLetter(bookTitle);
        String chapCode = WebCommonUtils.getFirstLetter(chapTitle);
        return bookCode + "-" + chapCode;
    }
}
