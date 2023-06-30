package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class VideoServiceTest {

    VideoService service;
    @Mock VideoRepository repository;

    @BeforeEach
    void setUp() {
        this.service = new VideoService(repository);
    }

    @Test
    void getVideosShouldReturnAll() {
        VideoEntity video1 = new VideoEntity("alice", "SpringBoot 3 Intro", "Learn the basics!");
        VideoEntity video2 = new VideoEntity("alice", "SpringBoot 3 Deep Dive", "Go deep!");
        when(repository.findAll()).thenReturn(List.of(video1, video2));
        List<VideoEntity> videos = service.getVideos();
        assertThat(videos).containsExactly(video1, video2);
    }

    @Test
    void deletingAVideoShouldWork() {
        VideoEntity entity = new VideoEntity("alice", "name", "desc");
        entity.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        // when
        service.delete(1L);
        // then
        verify(repository).findById(1L);
        verify(repository).delete(entity);
    }

}