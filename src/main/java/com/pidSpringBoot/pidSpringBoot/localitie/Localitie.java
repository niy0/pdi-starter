package com.pidSpringBoot.pidSpringBoot.localitie;

import com.pidSpringBoot.pidSpringBoot.location.Location;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "localities")
public class Localitie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = true,length = 6, name = "postal_code")
    private String postalCode;

    @Column(nullable = false,length = 60)
    private String locality;


    //Ajouter
    @OneToMany(targetEntity = Location.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "locality_id", referencedColumnName = "id")
    private List<Location> locations;

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

    @Override
    public String toString() {
        return "Localitie{" +
                "id=" + id +
                ", postalCode='" + postalCode + '\'' +
                ", locality='" + locality + '\'' +
                '}';
    }
}
