package com.karlchu.book.command;

import com.karlchu.book.dto.ChapterDTO;

/**
 * Created by KhanhChu on 1/5/2019.
 */
public class ChapterCommand extends AbstractCommand<ChapterDTO> {
    public ChapterCommand() {
        this.pojo = new ChapterDTO();
    }
}
