package com.pidSpringBoot.pidSpringBoot.location;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pidSpringBoot.pidSpringBoot.localitie.Localitie;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Set;

@Entity
@Table(name = "locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = true,length = 60)
    private String slug;

    @Column(nullable = false,length = 60)
    private String designation;

    @Column(nullable = false,length = 60)
    private String address;

    @Column(nullable = false,length = 11)
    private Integer locality_id;

    @Column(nullable = true,length = 255)
    private String website;

    @Column(nullable = false,length = 30)
    private String phone;

    /**
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "localitie_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Localitie localitie;

    public Location(){}
    public Location( String slug, String designation, String address, Integer locality_id, String website, String phone, Localitie localitie) {
        this.slug = slug;
        this.designation = designation;
        this.address = address;
        this.locality_id = this.localitie.getId();
        this.website = website;
        this.phone = phone;
    }**/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getLocality_id() {
        return locality_id;
    }

    public void setLocality_id(Integer locality_id) {
        this.locality_id = locality_id;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", slug='" + slug + '\'' +
                ", designation='" + designation + '\'' +
                ", address='" + address + '\'' +
                ", locality_id=" + locality_id +
                ", website='" + website + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
