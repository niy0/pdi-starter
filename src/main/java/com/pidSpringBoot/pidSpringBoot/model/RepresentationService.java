package com.pidSpringBoot.pidSpringBoot.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RepresentationService {
    @Autowired
    private RepresentationRepository representationRepository;

    public List<Representation> findAll() {
        return (List<Representation>) representationRepository.findAll();
    }

    public Representation findById(Long id) {
        Optional<Representation> optionalRepresentation = representationRepository.findById(id);
        return optionalRepresentation.orElseThrow(() -> new RuntimeException("Representation introuvable"));
    }

    public Representation save(Representation representation) {
        return representationRepository.save(representation);
    }

   /* public Representation update(Long id, Representation representation) {
        Representation representationToUpdate = findById(id);
        representationToUpdate.setShow(representation.getShow());
        representationToUpdate.setLocation(representation.getLocation());
        return representationRepository.save(representationToUpdate);
    }*/

    public void delete(Long id) {
        Representation representationToDelete = findById(id);
        representationRepository.delete(representationToDelete);
    }
}
