package com.karlchu.book.controller;

/**
 * Created by Khanh Chu on 12/27/2018.
 */

import com.karlchu.book.command.FeedbackCommand;
import com.karlchu.book.core.repository.FeedbackRepository;
import com.karlchu.book.core.service.FeedbackService;
import com.karlchu.book.dto.FeedbackDTO;
import com.karlchu.book.utility.Constants;
import com.karlchu.book.utility.RequestUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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

    @RequestMapping(value = "/public/feedback/edit")
    public ModelAndView editTip(@ModelAttribute(value = Constants.FORM_MODEL_KEY) FeedbackCommand command) {
        return new ModelAndView("/public/feedback/edit");
    }

    @RequestMapping(value = "/ajax/feedback/save")
    public void saveTip(@ModelAttribute(value = Constants.FORM_MODEL_KEY) FeedbackCommand command,
                        HttpServletRequest request, HttpServletResponse response) {
        String capResponse = request.getParameter(Constants.G_RECAPTCHA_RESPONSE);
        boolean isCaptchaValid = RequestUtil.verifyCaptcha(capResponse);
        try {
            response.setContentType("application/json; charset=utf-8");
            JSONObject obj = new JSONObject();
            PrintWriter out = response.getWriter();
            if (Constants.INSERT_OR_UPDATE.equals(command.getCrudAction())) {
                if (!validate(command)) {
                    obj.put("type", "danger");
                    obj.put("message", this.getMessageSourceAccessor().getMessage("feedback.save.data.empty"));
                }
                else if (!isCaptchaValid){
                    obj.put("type", "danger");
                    obj.put("message", this.getMessageSourceAccessor().getMessage("feedback.save.captcha.invalid"));
                }
                else {
                    try {
                        feedbackService.save(command.getPojo());
                        obj.put("type", "success");
                        obj.put("message", this.getMessageSourceAccessor().getMessage("feedback.save.successful"));
                    } catch (Exception e) {
                        log.error(e.getMessage(), e);
                        obj.put("type", "danger");
                        obj.put("message", this.getMessageSourceAccessor().getMessage("feedback.save.error"));
                    }
                }
            }
            out.write(obj.toString());
            out.flush();
            out.close();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } catch (JSONException e) {
            log.error(e.getMessage(), e);
        }
    }

    private boolean validate(FeedbackCommand command) {
        return StringUtils.isNotEmpty(command.getPojo().getName())
                && StringUtils.isNotEmpty(command.getPojo().getEmail())
                && StringUtils.isNotEmpty(command.getPojo().getContent());
    }

    @RequestMapping(value = "/admin/feedback/list")
    public ModelAndView list(@ModelAttribute(value = Constants.FORM_MODEL_KEY) FeedbackCommand command,
                             HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/admin/feedback/list");
        String crudAction = command.getCrudAction();
        try {
            if (Constants.ACTION_DELETE.equals(crudAction)) {
                String[] checkList = command.getCheckList();
                if (checkList != null && checkList.length > 0) {
                    Integer totalDeleteItem = 0;
//                    totalDeleteItem = feedbackRepository.deleteItems(command.getCheckList());
                    mav.addObject(Constants.ALTER, Constants.TYPE_SUCCESS);
                    mav.addObject(Constants.MESSAGE_RESPONSE, this.getMessageSourceAccessor().getMessage("feedback.delete.successful", new Object[]{totalDeleteItem}));
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
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
        Map<String, Object> properties = buildProperties(command);
        Object[] results = feedbackService.searchByPageAndSize(properties, command.getPage(), command.getMaxPageItems());
//        command.setListResult((List<FeedbackDTO>) results[1]);
//        command.setTotalItems(Integer.valueOf(results[0].toString()));
//        command.setTotalPages(Integer.valueOf(results[2].toString()));
    }

    private Map<String, Object> buildProperties(FeedbackCommand command) {
        Map<String, Object> properties = new HashMap<>();
        properties.put("fromDate", command.getFromDate());
        properties.put("toDate", command.getToDate());
        return properties;
    }
}