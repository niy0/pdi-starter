package com.pidSpringBoot.pidSpringBoot.model;

import jakarta.persistence.*;

@Entity
@Table(name="types")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String type;
    protected Type() { }

    public Type(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Type [id=" + id + ", type=" + type + "]";
    }

}
