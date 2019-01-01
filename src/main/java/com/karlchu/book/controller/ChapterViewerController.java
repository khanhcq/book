package com.karlchu.book.controller;

import com.karlchu.book.domain.BookRepository;
import com.karlchu.book.domain.BookRepositoryCustom;
import com.karlchu.book.domain.ChapterRepository;
import com.karlchu.book.domain.ChapterRepositoryCustom;
import com.karlchu.book.model.Book;
import com.karlchu.book.model.Chapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
        Book book = new Book();
        long id = this.bookRepositoryCustom.getMaxId() + 1;
        book.setId(id);
        book.setCode("TPTK-NCTHT");
        book.setTitle("Thôn phệ Tinh Không");
        this.bookRepository.insert(book);

        Chapter chapter = new Chapter();
        long chpId = this.chapterRepositoryCustom.getMaxId() + 1;
        chapter.setId(chpId);
        chapter.setBook(book);
        chapter.setCode("TPTK-001");
        chapter.setContent("<p><b>THÔN PHỆ TINH KHÔNG</b></p>\n");
        this.chapterRepository.insert(chapter);

        return "Inserted: " + book + "chapter" + chapter;
    }

    @RequestMapping(value = "/html-viewer.html")
    public ModelAndView htmlViewer(
            @RequestParam(value = "bookId", required = false) Long bookId,
            @RequestParam(value = "chapterId", required = false) Long chapterId) {
        ModelAndView mav = new ModelAndView("/viewer/html");
        Chapter chapter = chapterRepository.findByCode("TPTK-001");
        mav.addObject("content", chapter.getContent());
        return mav;
    }
}
