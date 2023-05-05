package com.pidSpringBoot.pidSpringBoot.model;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface RepresentationRepository extends CrudRepository<Representation, Long> {
   // List<Representation> findByShow(Show show);
  //  List<Representation> findByLocation(Location location);
   //List<Representation> findByShow(Show show);
   // List<Representation> findByLocation(Location location);
    List<Representation> findByWhen(LocalDateTime when);
}