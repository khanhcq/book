package com.karlchu.book.controller;

/**
 * Created by Khanh Chu on 12/27/2018.
 */

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Controller;

@Controller
public class ChapterController extends ApplicationObjectSupport {
    private transient final Log log = LogFactory.getLog(this.getClass());


}