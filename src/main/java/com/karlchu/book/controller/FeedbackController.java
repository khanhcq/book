package com.karlchu.book.controller;

/**
 * Created by Khanh Chu on 12/27/2018.
 */

import com.karlchu.book.command.FeedbackCommand;
import com.karlchu.book.core.repository.FeedbackRepository;
import com.karlchu.book.dto.FeedbackDTO;
import com.karlchu.book.utility.Constants;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class FeedbackController extends ApplicationObjectSupport {
    private transient final Log log = LogFactory.getLog(this.getClass());

    @Autowired
    private FeedbackRepository feedbackRepository;

    @RequestMapping(value = "/feedback/submit")
    public ModelAndView submit(FeedbackCommand command) {
        ModelAndView mav = new ModelAndView("/public/feedback/edit");
        if (Constants.INSERT_OR_UPDATE.equals(command.getCrudaction()) && validate(command)) {

        }
        return mav;
    }

    private boolean validate(FeedbackCommand command) {
        return StringUtils.isNotEmpty(command.getPojo().getName())
                && StringUtils.isNotEmpty(command.getPojo().getEmail())
                && StringUtils.isNotEmpty(command.getPojo().getContent());
    }

    @RequestMapping(value = "/feedback/list")
    public ModelAndView list(FeedbackCommand command) {
        ModelAndView mav = new ModelAndView("/admin/feedback/list");
//        Object[] results = feedbackRepository.findAll();
//        command.setListResult((List< FeedbackDTO>) results[1]);
//        command.setTotalItems(Integer.valueOf(results[0].toString()));
//        command.setTotalPages(Integer.valueOf(results[2].toString()));
        mav.addObject(Constants.LIST_MODEL_KEY, command);
        return mav;
    }
}