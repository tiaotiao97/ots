package com.ots.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/im/")
public class IMController {

    @RequestMapping(value = "im",method = RequestMethod.GET)
    public String im(){
        return "im";
    }

    @RequestMapping(value = "im2",method = RequestMethod.GET)
    public String im2(){
        return "im2";
    }

}
