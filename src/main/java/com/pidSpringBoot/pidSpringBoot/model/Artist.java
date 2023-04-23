package com.pidSpringBoot.pidSpringBoot.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@Entity
@Table(name="artists")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "The firstname must not be empty.")
    @Size(min=2, max=60, message = "The firstname must be between 2 and 60 characters long.")
    private String firstname;
    @NotEmpty(message = "The lastname must not be empty.")
    @Size(min=2, max=60, message = "The firstname must be between 2 and 60 characters long.")
    private String lastname;

    @ManyToMany(mappedBy = "artists")
    private List<Type> types = new ArrayList<>();

    public Artist(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public List<Type> getTypes(){
        return types;
    }

    public Artist addType(Type type) {
        if (!this.types.contains(type)) {
            this.types.add(type);
            type.addArtist(this);
        }

        return this;
    }

        public Artist removeType(Type type){
        if (!this.types.contains(type)){
            this.types.remove(type);
            type.getArtists().remove(this);
        }
        return this;
    }
}
