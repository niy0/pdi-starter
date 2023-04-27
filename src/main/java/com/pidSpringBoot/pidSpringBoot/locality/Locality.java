package com.pidSpringBoot.pidSpringBoot.locality;

import com.pidSpringBoot.pidSpringBoot.location.Location;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "localities")
public class Locality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private String postalCode;


    private String locality;


    //Ajouter
    @OneToMany( targetEntity=Location.class, mappedBy="locality" )
    private List<Location> locations = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }
    public Locality addLocation(Location location) {
        if(!this.locations.contains(location)) {
            this.locations.add(location);
            location.setLocality(this);
        }

        return this;
    }

    public Locality removeLocation(Location location) {
        if(this.locations.contains(location)) {
            this.locations.remove(location);
            if(location.getLocality().equals(this)) {
                location.setLocality(null);
            }
        }

        return this;
    }

    @Override
    public String toString() {
        return "Localitie{" +
                "id=" + id +
                ", postalCode='" + postalCode + '\'' +
                ", locality='" + locality + '\'' +
                '}';
    }
}
