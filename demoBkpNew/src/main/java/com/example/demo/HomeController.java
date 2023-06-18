package com.example.demo;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	record Video(String name) {}
	
	Video[] videoArray = { new Video("Need HELP with your SPRING BOOT 3 App?"),
            new Video("Don't do THIS to your own CODE!"),
            new Video("SECRETS to fix BROKEN CODE!") };
	List<Video> videos = Arrays.asList(videoArray);
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("videos", videos);
		return "index";
	}
}
