package com.karlchu.book.core.service.impl;

import com.karlchu.book.core.repository.UserRepository;
import com.karlchu.book.core.service.SequenceService;
import com.karlchu.book.core.service.UserService;
import com.karlchu.book.dto.UserDTO;
import com.karlchu.book.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * Created by BVDEV75 on 1/28/2019.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private SequenceService sequenceService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void save(UserDTO userDTO) {
        Users user = new Users();
        user.setId(sequenceService.getNextSequence("user"));
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setCreatedDate(new Date(System.currentTimeMillis()));
        this.repository.insert(user);

        //@TODO: emailing the user
    }

    @Override
    public Object[] searchByPageAndSize(Map<String, Object> properties, int page, int maxPageItems) {
        return new Object[0];
    }
}
