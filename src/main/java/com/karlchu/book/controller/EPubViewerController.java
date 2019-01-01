package com.karlchu.book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by KhanhChu on 1/1/2019.
 */
@Controller
public class EPubViewerController {

    @RequestMapping(value = { "/epub-viewer" }, method = RequestMethod.GET)
    public String viewPersonList(Model model) {
        return "/viewer/epub";
    }
}
