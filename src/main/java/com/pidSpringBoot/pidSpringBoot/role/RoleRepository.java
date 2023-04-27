package com.pidSpringBoot.pidSpringBoot.role;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository <Role,Integer>{

    //@Query("SELECT * From users_roles where r.role = ?1 ")
    //List<Role> findByName(String name);
}
