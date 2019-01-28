package com.karlchu.book.command;

import com.karlchu.book.dto.FeedbackDTO;

/**
 * Created by KhanhChu on 1/5/2019.
 */
public class FeedbackCommand extends AbstractCommand<FeedbackDTO> {
    public FeedbackCommand() {
        this.pojo = new FeedbackDTO();
    }
}
