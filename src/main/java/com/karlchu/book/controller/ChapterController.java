package com.karlchu.book.controller;

/**
 * Created by Khanh Chu on 12/27/2018.
 */

import com.karlchu.book.command.ChapterCommand;
import com.karlchu.book.core.repository.ChapterRepository;
import com.karlchu.book.core.repository.custom.ChapterRepositoryCustomImpl;
import com.karlchu.book.core.service.ChapterService;
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

import java.util.List;

@Controller
public class ChapterController extends ApplicationObjectSupport {
    private transient final Log log = LogFactory.getLog(this.getClass());

    @Autowired
    private ChapterService chapterService;

    @Autowired
    private ChapterRepository chapterRepository;

    @Autowired
    private ChapterRepositoryCustomImpl chapterRepositoryCustom;

    @RequestMapping(value = "/chapters")
    public ModelAndView list(@RequestParam(value = "page", required = false) Integer page, ChapterCommand command){
        ModelAndView mav = new ModelAndView("/admin/chapter/list");
        executeSearch(command, page);
        mav.addObject(Constants.LIST_MODEL_KEY, command);
        return mav;
    }

    private void executeSearch(ChapterCommand command, Integer page) {
        Object[] results = chapterService.searchByPageAndSize(page, command.getMaxPageItems());
        command.setListResult((List<ChapterDTO>) results[1]);
        command.setTotalItems(Integer.valueOf(results[0].toString()));
        command.setTotalPages(Integer.valueOf(results[2].toString()));
    }

    @RequestMapping(value = "/chapter")
    public ModelAndView htmlViewer(
            @RequestParam(value = "Id") Long chapterId) {
        ModelAndView mav = new ModelAndView("/viewer/html");
        Chapter chapter = chapterRepository.findById(chapterId).get();
        mav.addObject("content", chapter.getContent());
        mav.addObject("chapter", chapter);
        return mav;
    }

}