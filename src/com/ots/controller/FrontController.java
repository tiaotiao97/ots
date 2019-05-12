package com.ots.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/frontsite/")
public class FrontController {


    @RequestMapping("/{jspPageName}")
    public String index(@PathVariable String jspPageName){
        return "/frontsite/"+jspPageName;
    }

}
