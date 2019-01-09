package com.karlchu.book.controller;

/**
 * Created by Khanh Chu on 12/27/2018.
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @ResponseBody
    @RequestMapping("/")
    public String home() {
        return "";
    }


}