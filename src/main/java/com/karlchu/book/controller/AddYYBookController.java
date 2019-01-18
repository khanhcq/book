package com.karlchu.book.controller;

import com.karlchu.book.core.repository.BookRepository;
import com.karlchu.book.core.repository.ChapterRepository;
import com.karlchu.book.core.repository.custom.BookRepositoryCustom;
import com.karlchu.book.core.repository.custom.ChapterRepositoryCustom;
import com.karlchu.book.model.Book;
import com.karlchu.book.model.Chapter;
import com.karlchu.book.utility.WebCommonUtils;
import com.karlchu.crawler.utils.CrawlerUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created by KhanhChu on 1/1/2019.
 */
@Controller
public class AddYYBookController {

    @Autowired
    private BookRepositoryCustom bookRepositoryCustom;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ChapterRepositoryCustom chapterRepositoryCustom;

    @Autowired
    private ChapterRepository chapterRepository;

    @ResponseBody
    @RequestMapping("/addyy")
    public String testInsert() {
        String bookMainDir = "thien-tai-cao-thu";
        String bookDir = "D:\\" + bookMainDir;
        String introChapDir = bookDir + "\\" + bookMainDir + ".html";


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

        Document document = Jsoup.parse(stringBuilder.toString());
        Element bookNameE = document.select(".novel-info .info .name").first();
        String bookTitle = bookNameE.text().trim();
        Element authorE = document.select(".novel-info .info .author").first();
        String author = authorE.text().trim();

        Element cateE = document.select(".novel-info .info .tag-list .tag").first();
        String cate = cateE.text().trim();

        Element summaryE = document.select("#id_novel_summary").first();
        String summary = summaryE.text().trim();




        String bookCode = computeCode(author, bookTitle);
        Book book = bookRepository.findByCode(bookCode);
        if(book == null) {
            book = new Book();
            long id = this.bookRepositoryCustom.getMaxId() + 1;
            book.setId(id);
            book.setCode(bookCode);
            book.setTitle(bookTitle);

            book.setCategory(cate);
            book.setCategoryCode(WebCommonUtils.normalizeTitle(cate));

            book.setAuthor(author);
            book.setAuthorCode(WebCommonUtils.normalizeTitle(author));
            book.setDescription(summary);
            this.bookRepository.insert(book);
        }

        try {
            readChapter(book, bookDir, bookMainDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Done";
    }

    public void readChapter(Book book, String bookDir, String bookMainDir) throws IOException {
        File fBookDir = new File(bookDir);
        int i = 1;
        for (File fileEntry : fBookDir.listFiles()) {
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

//                StringBuilder out = CrawlerUtils.getTxtFiles(zip.getInputStream(entry));
                Document doc = Jsoup.parse(out.toString());
                String chapterTitle = doc.title();
                Chapter chapter = new Chapter();
                long chpId = this.chapterRepositoryCustom.getMaxId() + 1;
                chapter.setId(chpId);
                chapter.setChapterTitle(chapterTitle);
                chapter.setBookId(book.getId());
                chapter.setCode(computeCode(book.getTitle(), chapterTitle));

                Element chapContentE = doc.select("#id_chap_content .inner").first();
                chapter.setContent(chapContentE.html());
                chapter.setChapterNumber(i++);
                this.chapterRepository.insert(chapter);
            }
        }
    }

    public String computeCode(String bookTitle, String chapTitle) {
        String bookCode = WebCommonUtils.getFirstLetter(bookTitle);
        String chapCode = WebCommonUtils.getFirstLetter(chapTitle);
        return bookCode + "-" + chapCode;
    }



}
