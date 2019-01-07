package com.karlchu.book.controller;

import com.karlchu.book.core.repository.BookRepository;
import com.karlchu.book.core.repository.ChapterRepository;
import com.karlchu.book.core.repository.custom.BookRepositoryCustom;
import com.karlchu.book.core.repository.custom.ChapterRepositoryCustom;
import com.karlchu.book.model.Book;
import com.karlchu.book.model.Chapter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
            this.bookRepository.insert(book);
        }
        try {
            readChapter(book);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Inserted: " + book + "chapter";
    }

    public void readChapter(Book book) throws IOException {
        ZipFile zip = new ZipFile("src/main/resources/unzipTest/ThonPheTinhKhong-NgaCatTayHongThi.epub");
        for (Enumeration e = zip.entries(); e.hasMoreElements(); ) {
            ZipEntry entry = (ZipEntry) e.nextElement();
            if (!entry.isDirectory() &&
                    (entry.getName().endsWith(".xhtml") || entry.getName().endsWith(".html"))) {
                StringBuilder out = getTxtFiles(zip.getInputStream(entry));
                Document doc = Jsoup.parse(out.toString());
                String chapterTitle = doc.title();
                Chapter chapter = new Chapter();
                long chpId = this.chapterRepositoryCustom.getMaxId() + 1;
                chapter.setId(chpId);
                chapter.setChapterTitle(chapterTitle);
                chapter.setBookId(book.getId());
                chapter.setCode(computeCode(book.getTitle(), chapterTitle));
                chapter.setContent(doc.body().html());
                this.chapterRepository.insert(chapter);
            }
        }
    }

    private StringBuilder getTxtFiles(InputStream in) {
        StringBuilder out = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                out.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out;
    }

    public String computeCode(String bookTitle, String chapTitle) {
        String bookCode = getFirstLetter(bookTitle);
        String chapCode = getFirstLetter(chapTitle);
        return bookCode + "-" + chapCode;
    }

    public String getFirstLetter(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        Pattern p = Pattern.compile("\\b[A-Za-z]|[\\d]");
        Matcher m = p.matcher(s);

        while (m.find()) {
            stringBuilder.append(m.group());
        }
        return stringBuilder.toString().toLowerCase();
    }

    @RequestMapping(value = "/chapter.html")
    public ModelAndView htmlViewer(
            @RequestParam(value = "bookId", required = false) Long bookId,
            @RequestParam(value = "chapterId", required = false) Long chapterId) {
        ModelAndView mav = new ModelAndView("/viewer/html");
        Chapter chapter = chapterRepository.findAll().get(0);
        mav.addObject("content", chapter.getContent());
        mav.addObject("chapter", chapter);

        return mav;
    }
}
