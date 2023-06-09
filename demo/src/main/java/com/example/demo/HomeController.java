package com.example.demo;

import java.util.Arrays;
import java.util.List;

import jakarta.inject.Inject;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
	private final VideoService videoService;
	@Inject
	private  AppConfig appConfig;
	public HomeController(VideoService videoService) {
		this.videoService = videoService;
		this.appConfig = appConfig;
	}
	/*22
	@GetMapping
	public String index(Model model, Authentication authentication) {
		model.addAttribute("videos", videoService.getVideos());
		model.addAttribute("authentication", authentication);
		return "index";
	}*/
	
	@GetMapping("/")
	public String index(Model model, Authentication authentication) {
		model.addAttribute("videos", videoService.getVideos());
		model.addAttribute("authentication", authentication);
		model.addAttribute("header", appConfig.header());
		model.addAttribute("intro", appConfig.intro());
		return "index";
	}	
	
	@GetMapping("/react")
	public String react() {
		return "react";
	}
	
	@PostMapping("/new-video")
	public String newVideo(@ModelAttribute Video newVideo, Authentication authentication) {
		videoService.create(newVideo, authentication.getName());
		return "redirect:/";
	}
	
	@PostMapping("/multi-field-search")
	public String multiFieldSearch(@ModelAttribute VideoSearch search, Model model) {
		List<VideoEntity> searchResults = videoService.search(search);
		model.addAttribute("videos", searchResults);
		return "index";
	}
	
	@PostMapping("/universal-search")
	public String universalSearch(@ModelAttribute UniversalSearch search, Model model) {
		List<VideoEntity> searchResults = videoService.search(search);
		model.addAttribute("videos", searchResults);
		return "index";
	}
	
	@PostMapping("/delete/videos/{videoId}")
	public String deleteVideo(@PathVariable Long videoId) {
		videoService.delete(videoId);
		return "redirect:/";
	}
}