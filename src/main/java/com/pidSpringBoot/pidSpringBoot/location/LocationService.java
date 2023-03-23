package com.pidSpringBoot.pidSpringBoot.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {
    @Autowired
    private LocationRepository repository;

    public List<Location> listAll() {
        return (List<Location>) repository.findAll();
    }
}
