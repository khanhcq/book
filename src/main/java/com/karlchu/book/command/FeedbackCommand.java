package com.karlchu.book.command;

import com.karlchu.book.dto.FeedbackDTO;

import java.util.Date;

/**
 * Created by KhanhChu on 1/5/2019.
 */
public class FeedbackCommand extends AbstractCommand<FeedbackDTO> {
    public FeedbackCommand() {
        this.pojo = new FeedbackDTO();
    }

    private Date fromDate;
    private Date toDate;


    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }
}
