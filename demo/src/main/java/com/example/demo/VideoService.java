package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;


@Service
public class VideoService {
	Video[] videoArray = { new Video("Need HELP with your SPRING BOOT 3 App?"),
            new Video("Don't do THIS to your own CODE!"),
            new Video("SECRETS to fix BROKEN CODE!") };
	List<Video> videos = Arrays.asList(videoArray);
	
	public List<Video> getVideos(){
		return videos;
	}
	
	public Video create(Video newVideo) {
		List<Video> extend = new ArrayList<>(videos);
		extend.add(newVideo);
		this.videos = List.copyOf(extend);
		return newVideo;
	}
}