package com.pidSpringBoot.pidSpringBoot.role;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository <Role,Integer>{

    //@Query("SELECT * From users_roles where r.role = ?1 ")
    //List<Role> findByName(String name);
}
