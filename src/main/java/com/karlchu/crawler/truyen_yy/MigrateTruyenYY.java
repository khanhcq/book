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
public class MigrateTruyenYY {
    private final static String DB_NAME = "stories";
    private final static String MY_SEQUENCE_COLLECTION = "sequence";
    private final static String BOOK_COLLECTION = "book";
    private final static String CHAPTER_COLLECTION = "chapter";

    public static void main (String [] args) {
        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
        MongoDatabase db = mongoClient.getDatabase(DB_NAME);
        testInsert(db, "thien-tai-cao-thu");
    }

    private static String testInsert(MongoDatabase db, String bookName) {
        String bookDir = "D:\\" + bookName;
        String introChapDir = bookDir + "\\" + bookName + ".html";
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(introChapDir));
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        org.jsoup.nodes.Document document = Jsoup.parse(stringBuilder.toString());
        Element bookNameE = document.select(".novel-info .info .name").first();
        String bookTitle = bookNameE.text().trim();
        Element authorE = document.select(".novel-info .info .author").first();
        String sAuthor = authorE.text().trim();
        Author author = new Author(sAuthor, WebCommonUtils.normalizeTitle(sAuthor));
        Element cateE = document.select(".novel-info .info .tag-list").first();
        List<Category> categories = new ArrayList<>();
        String tag;
        for(Element e : cateE.select(".tag")){
            tag = e.text().trim();
            Category category = new Category(tag, WebCommonUtils.normalizeTitle(tag));
            categories.add(category);
        }

        Element summaryE = document.select("#id_novel_summary").first();
        String summary = summaryE.text().trim();

        MongoCollection<Document> bookCollection = db.getCollection(BOOK_COLLECTION);
        String bookCode = computeCode(author.getName(), bookTitle);
        Document dBook = bookCollection.find(Filters.eq("code", bookCode)).first();
        Book book;
        if(dBook == null) {
            book = new Book();
            book.setId(Long.valueOf(getNextSequence(BOOK_COLLECTION, db).toString()));
            book.setCode(bookCode);
            book.setTitle(bookTitle);
            book.setCategories(categories);
            book.setAuthor(author);
            book.setDescription(summary);
            bookCollection.insertOne(createBookDocument(book));
        } else {
            book = getBookFromDocument(dBook);
        }

        try {
            readChapter(db, book, bookDir, bookName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Done";
    }

    private static Book getBookFromDocument(Document dBook) {
        Book book = new Book();
        book.setId((Long) dBook.get("_id"));
        book.setTitle((String) dBook.get("title"));
        return book;
    }

    private static Document createCategoryDocument(Category category){
        Document doc = new Document();
        doc.append("code", category.getCode());
        doc.append("name", category.getName());
        return doc;
    }

    private static Document createAuthorDocument(Author author){
        Document doc = new Document();
        doc.append("code", author.getCode());
        doc.append("name", author.getName());
        return doc;
    }

    private static Document createBookDocument(Book book){
        Document doc = new Document();
        doc.append("_id", book.getId());
        doc.append("code", book.getCode());
        doc.append("title", book.getTitle());
        ArrayList<Document> categories = new ArrayList<>();
        for(Category category : book.getCategories()){
            categories.add(createCategoryDocument(category));
        }
        doc.append("categories", categories);
        doc.append("author", createAuthorDocument(book.getAuthor()));
        doc.append("description", book.getDescription());
        doc.append("_class", book.getClass().getCanonicalName());

        return doc;
    }

    private static void readChapter(MongoDatabase db, Book book, String bookDir, String bookMainDir) throws IOException {
        MongoCollection<Document> chapterCollection = db.getCollection(CHAPTER_COLLECTION);
        Pattern letter = Pattern.compile("[a-zA-z]");
        File fBookDir = new File(bookDir);
        File[] files = fBookDir.listFiles();
        List<File> fileList = Arrays.asList(files);
        Collections.sort(fileList, new Comparator<File>() {
            @Override
            public int compare(File f1, File f2) {
                String name1 = f1.getName().replaceAll("\\.html", "");
                String name2 = f2.getName().replaceAll("\\.html", "");
                int i1 = 0;
                int i2 = 0;
                Matcher hasLetter1 = letter.matcher(name1);
                Matcher hasLetter2 = letter.matcher(name2);
                if(!hasLetter1.find()){
                    i1 = Integer.valueOf(name1);
                }
                if(!hasLetter2.find()){
                    i2 = Integer.valueOf(name2);
                }
                int res = i1 > i2 ? 1 : -1;
                return res;
            }
        });
        for (File fileEntry : fileList) {
            if (fileEntry.isFile() && !fileEntry.getName().equals(bookMainDir + ".html")) {

                StringBuilder out = new StringBuilder();
                String line;
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(fileEntry.getPath()));
                    while ((line = reader.readLine()) != null) {
                        out.append(line);
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }

                org.jsoup.nodes.Document doc = Jsoup.parse(out.toString());
                String chapterTitle = doc.select("#id_chap_content .chapter-title").first().text().trim();

                String chapterCode = computeCode(book.getTitle(), chapterTitle);
                Document dChapter = chapterCollection.find(Filters.eq("code", chapterCode)).first();
                Chapter chapter;
                if(dChapter == null) {
                    chapter = new Chapter();
                    chapter.setId(Long.valueOf(getNextSequence(CHAPTER_COLLECTION, db).toString()));
                    chapter.setTitle(chapterTitle);
                    chapter.setBookId(book.getId());
                    chapter.setCode(chapterCode);

                    Element chapContentE = doc.select("#id_chap_content .inner").first();
                    chapter.setContent(chapContentE.html());
                    chapter.setNumber(Integer.valueOf(WebCommonUtils.normalizeTitle(chapterTitle).split("-")[1]));
                    chapterCollection.insertOne(createChapterDocument(chapter));
                }
            }
        }
    }

    private static Document createChapterDocument(Chapter chapter) {
        Document doc = new Document();
        doc.append("_id", chapter.getId());
        doc.append("title", chapter.getTitle());
        doc.append("bookId", chapter.getBookId());
        doc.append("code", chapter.getCode());
        doc.append("content", chapter.getContent());
        doc.append("number", chapter.getNumber());
        doc.append("_class", chapter.getClass().getCanonicalName());


        return doc;
    }

    private static String computeCode(String bookTitle, String chapTitle) {
        String bookCode = WebCommonUtils.getFirstLetter(bookTitle);
        String chapCode = WebCommonUtils.getFirstLetter(chapTitle);
        return bookCode + "-" + chapCode;
    }


    private static void createCountersCollection(MongoCollection<Document> countersCollection, String collectionName) {
        Document document = new Document();
        document.append("_id", collectionName);
        document.append("seq", 1l);
        countersCollection.insertOne(document);
    }

    private static Object getNextSequence(String collectionName, MongoDatabase database) {
        MongoCollection<Document> countersCollection = database.getCollection(MY_SEQUENCE_COLLECTION);
        Document doc = countersCollection.find(Filters.eq("_id", collectionName)).first();

        if (doc == null) {
            createCountersCollection(countersCollection, collectionName);
        }
        Document searchQuery = new Document("_id", collectionName);
        Document increase = new Document("seq", 1l);
        Document updateQuery = new Document("$inc", increase);
        Document result = countersCollection.findOneAndUpdate(searchQuery, updateQuery);
        return result.get("seq");
    }
}
