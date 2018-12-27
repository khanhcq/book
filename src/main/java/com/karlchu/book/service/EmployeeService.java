package com.karlchu.book.service;

import com.karlchu.book.domain.EmployeeRepository;
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
