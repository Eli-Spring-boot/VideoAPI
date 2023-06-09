package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

public class CoreDomainTest {
	@Test
	void newVideoEntityShouldHaveNullId() {
		VideoEntity entity = new VideoEntity("alice","title","description");
		assertThat(entity.getId()).isNull();
		assertThat(entity.getUsername()).isEqualTo("alice");
		assertThat(entity.getDescription()).isEqualTo("description");
	}
	
	@Test
	void settersShouldMutateState() {
		VideoEntity entity = new VideoEntity("alice","title","description");
		entity.setId(99L);
		entity.setName("new name");
		entity.setDescription("new desc");
		entity.setUsername("bob");
		assertThat(entity.getId()).isEqualTo(99L);
		assertThat(entity.getUsername()).isEqualTo("bob");
		assertThat(entity.getName()).isEqualTo("new name");
		assertThat(entity.getDescription()).isEqualTo("new desc");

	}
	/*
	@Test
	void settersShouldMutateState() {
		VideoEntity entity = new VideoEntity("alice","title","description");
		entity.setId(99L);
		entity.setName("new name");
		entity.setDescription("new desc");
		entity.setUsername("bob");
		assertThat(entity.getId()).isEqualTo(99L);
		assertThat(entity.getUsername()).isEqualTo("bob");
		assertThat(entity.getName()).isEqualTo("new name");
		assertThat(entity.getDescription()).isEqualTo("new desc");
	}*/
}