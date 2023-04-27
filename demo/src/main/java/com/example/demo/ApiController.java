package com.example.demo;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
	private final VideoService videoService;
	
	public ApiController(VideoService videoService) {
		this.videoService = videoService;
	}
	
	@GetMapping("/api/videos")
	//@CrossOrigin(origins="http://localhost:3000")
	public List<Video> all(){
		return videoService.getVideos();
	}
	
	@PostMapping("/api/videos")
	//@CrossOrigin(origins="http://localhost:3000")
	public Video newVideo(@RequestBody Video newVideo) {
		return videoService.create(newVideo);
	}
}
