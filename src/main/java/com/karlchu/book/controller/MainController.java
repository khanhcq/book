package com.karlchu.book.controller;

/**
 * Created by Khanh Chu on 12/27/2018.
 */
import java.util.Date;
import java.util.List;

import com.karlchu.book.domain.EmployeeRepository;
import com.karlchu.book.domain.EmployeeRepositoryCustom;
import com.karlchu.book.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    private static final String[] NAMES = { "Tom", "Jerry", "Donald" };

    @Autowired
    private EmployeeRepositoryCustom employeeRepositoryCustom;

    @Autowired
    private EmployeeRepository employeeRepository;

    @ResponseBody
    @RequestMapping("/")
    public String home() {
        return "";
    }

    @RequestMapping(value = { "/employeeList" }, method = RequestMethod.GET)
    public ModelAndView list(){
        ModelAndView mav = new ModelAndView("employeeList");
        List<Employee> employees = this.employeeRepository.findAll();
        mav.addObject("employees", employees);
        return mav;
    }

}