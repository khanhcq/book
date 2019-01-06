package com.karlchu.book.core.service;

import com.karlchu.book.core.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    public List getEmployees() {
        return repository.findAll();
    }
}
