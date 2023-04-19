package com.pidSpringBoot.pidSpringBoot.role;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 30, nullable = false)
    private String role;

    public Role() {}

    public Role(Integer id) {
        this.id = id;
    }

    public Role(String role) {
        this.role = role;
    }


    public Integer getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
