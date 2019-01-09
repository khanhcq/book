package com.karlchu.book.controller;

/**
 * Created by Khanh Chu on 12/27/2018.
 */

import com.karlchu.book.command.ChapterCommand;
import com.karlchu.book.core.service.BookService;
import com.karlchu.book.dto.ChapterDTO;
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

    @RequestMapping(value = "/books")
    public ModelAndView list(@RequestParam(value = "page", required = false) Integer page,
                             ChapterCommand command){
        ModelAndView mav = new ModelAndView("/public/books");
        executeSearch(command, page);
        mav.addObject(Constants.LIST_MODEL_KEY, command);
        return mav;
    }

    private void executeSearch(ChapterCommand command, Integer page) {
        Object[] results = bookService.searchByPageAndSize(page, command.getMaxPageItems());
        command.setListResult((List<ChapterDTO>) results[1]);
        command.setTotalItems(Integer.valueOf(results[0].toString()));
        command.setTotalPages(Integer.valueOf(results[2].toString()));
    }

}