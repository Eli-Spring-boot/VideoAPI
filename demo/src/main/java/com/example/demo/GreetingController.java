package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GreetingController {

    @GetMapping("/greeting")
    public String greeting(Model model) {
        List<String> names = new ArrayList<>();
        names.add("name1");
        names.add("name2");
        names.add("name3");
        model.addAttribute("names", names);
        return "greeting";
    }
}
