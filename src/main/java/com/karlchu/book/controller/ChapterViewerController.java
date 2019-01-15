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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created by KhanhChu on 1/1/2019.
 */
@Controller
public class ChapterViewerController {

    @Autowired
    private BookRepositoryCustom bookRepositoryCustom;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ChapterRepositoryCustom chapterRepositoryCustom;

    @Autowired
    private ChapterRepository chapterRepository;

    @ResponseBody
    @RequestMapping("/addBook")
    public String testInsert() {
        String TPTK_NCTHT = "TPTK-NCTHT";
        Book book = bookRepository.findByCode(TPTK_NCTHT);
        if(book == null) {
            book = new Book();
            long id = this.bookRepositoryCustom.getMaxId() + 1;
            book.setId(id);
            book.setCode(TPTK_NCTHT);
            book.setTitle("Thôn phệ Tinh Không");

            String cate = "Huyền Huyễn";
            book.setCategory(cate);
            book.setCategoryCode(WebCommonUtils.normalizeTitle(cate));

            String author = "Ngã Cật Tây Hồng Thị";
            book.setAuthor(author);
            book.setAuthorCode(WebCommonUtils.normalizeTitle(author));

            this.bookRepository.insert(book);
        }

//        try {
//            String bookURI = "src/main/resources/unzipTest/ThonPheTinhKhong-NgaCatTayHongThi.epub";
//            readChapter(book, bookURI);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return "Done";
    }

    public void readChapter(Book book, String bookURI) throws IOException {
        ZipFile zip = new ZipFile(bookURI);
        int i = 1;
        for (Enumeration e = zip.entries(); e.hasMoreElements(); ) {
            ZipEntry entry = (ZipEntry) e.nextElement();
            if (!entry.isDirectory() &&
                    (entry.getName().endsWith(".xhtml") || entry.getName().endsWith(".html"))) {
                StringBuilder out = CrawlerUtils.getTxtFiles(zip.getInputStream(entry));
                Document doc = Jsoup.parse(out.toString());
                String chapterTitle = doc.title();
                Chapter chapter = new Chapter();
                long chpId = this.chapterRepositoryCustom.getMaxId() + 1;
                chapter.setId(chpId);
                chapter.setChapterTitle(chapterTitle);
                chapter.setBookId(book.getId());
                chapter.setCode(computeCode(book.getTitle(), chapterTitle));
                chapter.setContent(doc.body().html());
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
