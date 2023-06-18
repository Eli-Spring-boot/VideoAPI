package com.example.demofake;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demofake.VideoEntity;
import com.example.demofake.VideoService;

@Controller
public class HomeController {
private final VideoService videoService;
	
	public HomeController(VideoService videoService) {
		this.videoService = videoService;
	}
	
	@GetMapping("/")
	public String index(Model model) {
		List<VideoEntity> videoList = videoService.getVideos();
		for (VideoEntity video : videoList) {
		    System.out.println(video.getName());
		}
		model.addAttribute("videos", videoService.getVideos());
		return "index";
	}
	
	@PostMapping("/delete/videos/{videoId}")
	public String deleteVideo(@PathVariable Long videoId) {
		//videoService.delete(videoId);
		return "redirect:/";
	}
}