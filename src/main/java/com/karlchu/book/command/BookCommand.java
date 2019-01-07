package com.karlchu.book.command;

import com.karlchu.book.dto.BookDTO;
import com.karlchu.book.model.Book;

/**
 * Created by KhanhChu on 1/5/2019.
 */
public class BookCommand extends AbstractCommand<BookDTO> {
    public BookCommand() {
        this.pojo = new BookDTO();
    }
}
