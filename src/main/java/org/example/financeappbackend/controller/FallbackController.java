package org.example.financeappbackend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FallbackController {

    @GetMapping("/{path:[^\\.]*}")
    public String redirect() {
        return "forward:/";
    }
}
