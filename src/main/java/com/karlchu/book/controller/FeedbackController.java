package com.karlchu.book.controller;

/**
 * Created by Khanh Chu on 12/27/2018.
 */

import com.karlchu.book.command.FeedbackCommand;
import com.karlchu.book.core.repository.FeedbackRepository;
import com.karlchu.book.core.service.FeedbackService;
import com.karlchu.book.dto.FeedbackDTO;
import com.karlchu.book.utility.Constants;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class FeedbackController extends ApplicationObjectSupport {
    private transient final Log log = LogFactory.getLog(this.getClass());

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private FeedbackService feedbackService;

    @RequestMapping(value = "/feedback/edit")
    public ModelAndView editTip(@ModelAttribute(value = Constants.FORM_MODEL_KEY) FeedbackCommand command,
                                RedirectAttributes redirectAttributes){
        ModelAndView mav = new ModelAndView("/public/feedback/edit");
        FeedbackDTO pojo = command.getPojo();
        try{
            if (Constants.INSERT_OR_UPDATE.equals(command.getCrudAction())){
                if (validate(command)){
                    feedbackService.save(pojo);
                    redirectAttributes.addFlashAttribute(Constants.ALTER, Constants.TYLE_SUCCESS);
                    redirectAttributes.addFlashAttribute(Constants.MESSAGE_RESPONSE, this.getMessageSourceAccessor().getMessage("feedback.save.successful"));
                    return new ModelAndView("redirect:/feedback/edit");
                }
            }
        }catch (Exception e){
            log.error(e.getMessage(), e);
            mav.addObject(Constants.ALTER, Constants.TYLE_DANGER);
            mav.addObject(Constants.MESSAGE_RESPONSE, this.getMessageSourceAccessor().getMessage("database.exception"));
        }
        return mav;
    }

    private boolean validate(FeedbackCommand command) {
        return StringUtils.isNotEmpty(command.getPojo().getName())
                && StringUtils.isNotEmpty(command.getPojo().getEmail())
                && StringUtils.isNotEmpty(command.getPojo().getContent());
    }

    @RequestMapping(value = "/admin/feedback/list")
    public ModelAndView list(@ModelAttribute(value = Constants.FORM_MODEL_KEY)FeedbackCommand command,
                             HttpServletRequest request){
        ModelAndView mav = new ModelAndView("/admin/feedback/list");
        String crudAction = command.getCrudAction();
        try {
            if (Constants.ACTION_DELETE.equals(crudAction)){
                String[] checkList = command.getCheckList();
                if(checkList != null && checkList.length > 0) {
                    Integer totalDeleteItem = 0;
//                    totalDeleteItem = feedbackRepository.deleteItems(command.getCheckList());
                    mav.addObject(Constants.ALTER, Constants.TYPE_SUCCESS);
                    mav.addObject(Constants.MESSAGE_RESPONSE, this.getMessageSourceAccessor().getMessage("feedback.delete.successful", new Object[]{totalDeleteItem}));
                }
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
            mav.addObject(Constants.ALTER, Constants.TYPE_DANGER);
            mav.addObject(Constants.MESSAGE_RESPONSE, this.getMessageSourceAccessor().getMessage("feedback.delete.unsuccessful"));
        }
        referenceData(command, mav);
        executeSearch(command, request);
        mav.addObject(Constants.LIST_MODEL_KEY, command);
        return mav;
    }

    private void referenceData(FeedbackCommand command, ModelAndView mav) {
//        mav.addObject("tipCategories", tipCategoryManagementLocalBean.findAll());
    }

    private void executeSearch(FeedbackCommand command, HttpServletRequest request) {
//        RequestUtil.initSearchBean(request, command);
        Map<String, Object> properties = buildPropertites(command);
        Object[] results = feedbackService.searchByPageAndSize(properties, command.getPage(), command.getMaxPageItems());
        command.setListResult((List<FeedbackDTO>) results[1]);
        command.setTotalItems(Integer.valueOf(results[0].toString()));
        command.setTotalPages(Integer.valueOf(results[2].toString()));
    }

    private Map<String, Object> buildPropertites(FeedbackCommand command) {
        Map<String, Object> properties = new HashMap<>();
        properties.put("status", command.getPojo().getStatus());
        properties.put("fromDate", command.getFromDate());
        properties.put("toDate", command.getToDate());
        return properties;
    }
}