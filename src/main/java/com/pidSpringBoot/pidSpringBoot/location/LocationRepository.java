package com.pidSpringBoot.pidSpringBoot.location;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends CrudRepository <Location, Integer>{
    public Integer countById(Integer id);
}
