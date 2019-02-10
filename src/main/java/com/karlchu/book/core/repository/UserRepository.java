package com.karlchu.book.core.repository;

import com.karlchu.book.model.Book;
import com.karlchu.book.model.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by KhanhChu on 1/1/2019.
 */
public interface UserRepository extends MongoRepository<Users, Long> {
    Users findByEmail(String email);

}
