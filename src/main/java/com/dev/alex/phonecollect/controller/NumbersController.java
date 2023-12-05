package com.dev.alex.phonecollect.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NumbersController {
    @GetMapping("/number")
    public String getNumbersAddPage(){
        return "numbers";
    }
}
