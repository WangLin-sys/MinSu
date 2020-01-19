package com.jk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("htpage")
public class PageController {

    @RequestMapping("login")
    public String goindex(){

        return "htlogin";
    }





}
