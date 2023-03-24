package com.pidSpringBoot.pidSpringBoot.show;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ShowRepository extends CrudRepository<Show, Long>{
	Show findBySlug(String slug);
	Show findByTitle(String title);
	//List<Show> findByLocation(Location location);
}

