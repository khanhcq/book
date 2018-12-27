package com.karlchu.book.controller;

/**
 * Created by BVDEV75 on 12/27/2018.
 */
import java.util.Date;
import java.util.List;

import com.karlchu.book.domain.EmployeeRepository;
import com.karlchu.book.domain.EmployeeRepositoryCustom;
import com.karlchu.book.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
        String html = "";
        html += "<ul>";
        html += " <li><a href='/testInsert'>Test Insert</a></li>";
        html += " <li><a href='/showAllEmployee'>Show All Employee</a></li>";
        html += " <li><a href='/showFullNameLikeTom'>Show All 'Tom'</a></li>";
        html += " <li><a href='/deleteAllEmployee'>Delete All Employee</a></li>";
        html += "</ul>";
        return html;
    }

    @ResponseBody
    @RequestMapping("/testInsert")
    public String testInsert() {
        Employee employee = new Employee();

        long id = this.employeeRepositoryCustom.getMaxEmpId() + 1;
        int idx = (int) (id % NAMES.length);
        String fullName = NAMES[idx] + " " + id;

        employee.setId(id);
        employee.setEmpNo("E" + id);
        employee.setFullName(fullName);
        employee.setHireDate(new Date());
        this.employeeRepository.insert(employee);

        return "Inserted: " + employee;
    }

    @ResponseBody
    @RequestMapping("/showAllEmployee")
    public String showAllEmployee() {

        List<Employee> employees = this.employeeRepository.findAll();

        String html = "";
        for (Employee emp : employees) {
            html += emp + "<br>";
        }

        return html;
    }

    @ResponseBody
    @RequestMapping("/showFullNameLikeTom")
    public String showFullNameLikeTom() {

        List<Employee> employees = this.employeeRepository.findByFullNameLike("Tom");

        String html = "";
        for (Employee emp : employees) {
            html += emp + "<br>";
        }

        return html;
    }

    @ResponseBody
    @RequestMapping("/deleteAllEmployee")
    public String deleteAllEmployee() {

        this.employeeRepository.deleteAll();
        return "Deleted!";
    }

    @RequestMapping(value = { "/employeeList" }, method = RequestMethod.GET)
    public String viewPersonList(Model model) {
        List<Employee> employees = this.employeeRepository.findAll();
        model.addAttribute("employees", employees);
        return "employeeList";
    }

}