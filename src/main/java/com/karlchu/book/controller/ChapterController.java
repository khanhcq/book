package com.karlchu.book.controller;

/**
 * Created by Khanh Chu on 12/27/2018.
 */

import com.karlchu.book.command.ChapterCommand;
import com.karlchu.book.core.repository.BookRepository;
import com.karlchu.book.core.repository.EmployeeRepository;
import com.karlchu.book.core.service.ChapterService;
import com.karlchu.book.dto.ChapterDTO;
import com.karlchu.book.model.Employee;
import com.karlchu.book.utility.Constants;
import com.karlchu.book.utility.RequestUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ChapterController extends ApplicationObjectSupport {
    private transient final Log log = LogFactory.getLog(this.getClass());

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ChapterService chapterService;

    @InitBinder
    public void initBinder(WebDataBinder binder){
    }

    @RequestMapping(value = "/chapter/list.html")
    public ModelAndView list(ChapterCommand command,
                             HttpServletRequest request){
        ModelAndView mav = new ModelAndView("/admin/chapter/list");
        executeSearch(command, request);
        mav.addObject(Constants.LIST_MODEL_KEY, command);
        return mav;
    }

    private void executeSearch(ChapterCommand command, HttpServletRequest request) {
        RequestUtil.initSearchBean(request, command);
        Map<String, Object> properties = buildPropertites(command);
        Object[] results = chapterService.searchByProperties(properties, command.getSortExpression(), command.getSortDirection(), command.getPage(), command.getMaxPageItems());
        command.setListResult((List<ChapterDTO>) results[1]);
        command.setTotalItems(Integer.valueOf(results[0].toString()));
    }

    private Map<String, Object> buildPropertites(ChapterCommand command) {
        Map<String, Object> properties = new HashMap();
//        if (StringUtils.isNotBlank(command.getPojo().getName())){
//            properties.put("name", command.getPojo().getName());
//        }
        return properties;
    }

}