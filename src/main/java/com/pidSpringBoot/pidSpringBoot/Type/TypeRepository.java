package com.pidSpringBoot.pidSpringBoot.Type;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TypeRepository extends CrudRepository<Type, Long> {
    Type findByType(String type);
    Optional<Type> findById(Long id);
}

