package com.karlchu.book.controller;

/**
 * Created by Khanh Chu on 12/27/2018.
 */

import com.karlchu.book.command.ChapterCommand;
import com.karlchu.book.core.repository.BookRepository;
import com.karlchu.book.core.repository.ChapterRepository;
import com.karlchu.book.core.service.BookService;
import com.karlchu.book.core.service.ChapterService;
import com.karlchu.book.core.utils.CoreUtils;
import com.karlchu.book.dto.ChapterDTO;
import com.karlchu.book.model.Chapter;
import com.karlchu.book.utility.Constants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class BookController extends ApplicationObjectSupport {
    private transient final Log log = LogFactory.getLog(this.getClass());

    @Autowired
    private BookService bookService;

    @Autowired
    private ChapterService chapterService;

    @Autowired
    private ChapterRepository chapterRepository;

    @Autowired
    private BookRepository bookRepository;

    @RequestMapping(value = {"/books","/"})
    public ModelAndView list(@RequestParam(value = "page", required = false) Integer page,
                             @RequestParam(value = "category", required = false) String category,
                             @RequestParam(value = "author", required = false) String author,
                             ChapterCommand command){
        ModelAndView mav = new ModelAndView("/public/books");
        Object[] results = bookService.searchByPageAndSize(page, command.getMaxPageItems(), category, author);
        command.setListResult((List<ChapterDTO>) results[1]);
        command.setTotalItems(Integer.valueOf(results[0].toString()));
        command.setTotalPages(Integer.valueOf(results[2].toString()));
        mav.addObject(Constants.LIST_MODEL_KEY, command);
        mav.addObject("category", category);
        mav.addObject("author", author);

        return mav;
    }

    @RequestMapping(value = "/book")
    public ModelAndView list(@RequestParam(value = "page", required = false) Integer page,
                             @RequestParam(value = "id") Long bookId,
                             ChapterCommand command){
        ModelAndView mav = new ModelAndView("/admin/chapter/list");
        Object[] results = chapterService.searchByPageAndSize(bookId, page, command.getMaxPageItems());
        command.setListResult((List<ChapterDTO>) results[1]);
        command.setTotalItems(Integer.valueOf(results[0].toString()));
        command.setTotalPages(Integer.valueOf(results[2].toString()));
        mav.addObject(Constants.LIST_MODEL_KEY, command);
        mav.addObject("book", bookRepository.findById(bookId).get());

        return mav;
    }

    @RequestMapping(value = "/chapter")
    public ModelAndView htmlViewer(
            @RequestParam(value = "id") Long bookId,
            @RequestParam(value = "no") Integer chapterNo) {
        ModelAndView mav = new ModelAndView("/viewer/html");
        Chapter chapter = chapterRepository.findByBookIdAndNumber(bookId, chapterNo);
        mav.addObject("chapter", chapter);
        mav.addObject("lastChapter", chapterRepository.count(CoreUtils.getChapterExample(bookId)));
        mav.addObject("book", bookRepository.findById(chapter.getBookId()).get());
        return mav;
    }

}