package com.karlchu.book.controller;

/**
 * Created by Khanh Chu on 12/27/2018.
 */

import com.karlchu.book.core.repository.EmployeeRepository;
import com.karlchu.book.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MainController {

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