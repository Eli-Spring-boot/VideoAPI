package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class VideoService {
	private final VideoRepository repository;
	
	Video[] videoArray = { new Video("Need HELP with your SPRING BOOT 3 App?",""),
            new Video("Don't do THIS to your own CODE!",""),
            new Video("SECRETS to fix BROKEN CODE!","") };
	List<Video> videos = Arrays.asList(videoArray);
	
	public VideoService(VideoRepository repository) {
		this.repository = repository;
	}
	
	public List<VideoEntity> getVideos(){
		return repository.findAll();
	}
	
	public VideoEntity create(Video newVideo, String username) {
		return repository.saveAndFlush(new VideoEntity(username, newVideo.name(), newVideo.description()));
		/*List <Video> extend = new ArrayList<>(videos);
		extend.add(newVideo);
		this.videos = List.copyOf(extend);
		return newVideo;*/
	}
	
	public List<VideoEntity> search(UniversalSearch search){
		VideoEntity probe = new VideoEntity();
		if (StringUtils.hasText(search.value())) {
			probe.setName(search.value());
			probe.setDescription(search.value());
		}
		
		Example<VideoEntity> example = Example.of(probe,
				ExampleMatcher.matchingAny()
				.withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING));
		return repository.findAll(example);
	}
	public List<VideoEntity> search(VideoSearch videoSearch){
		if (StringUtils.hasText(videoSearch.name()) && StringUtils.hasText(videoSearch.description())) {
			return repository.findByNameContainsOrDescriptionContainsAllIgnoreCase(videoSearch.name(), videoSearch.description());
		}
		
		if (StringUtils.hasText(videoSearch.name())) {
			return repository.findByNameContainsIgnoreCase(videoSearch.name());
		}
		
		if (StringUtils.hasText(videoSearch.description())) {
			return repository.findByDescriptionContainsIgnoreCase(videoSearch.description());
		}
		
		return Collections.emptyList();
	}
	
	public void delete(Long videoId) {
		repository.findById(videoId).map(videoEntity->{
			repository.delete(videoEntity);
			return true;
		})
		.orElseThrow(()->new RuntimeException("No video at " + videoId));
	}
}