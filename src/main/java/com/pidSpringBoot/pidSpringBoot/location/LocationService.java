package com.pidSpringBoot.pidSpringBoot.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {
    @Autowired
    private LocationRepository repository;

    public List<Location> listAll() {
        return (List<Location>) repository.findAll();
    }

    public void save(Location location) {
        repository.save(location);
    }

    public Location get(Integer id) throws LocationNotFoundException {
        Optional<Location> location_result = repository.findById(id);
        if(location_result.isPresent()) {
            return location_result.get();
        }
        throw new LocationNotFoundException("Pas de location avec cette Id"+ id);
    }


    public void delete(Integer id) throws LocationNotFoundException {
        Long count = repository.countById(id);
        if (count == null || count == 0 ) {
            throw new LocationNotFoundException("Location avec cette Id"+ id +" a été supprimé.");
        }
        repository.deleteById(id);
    }
}
