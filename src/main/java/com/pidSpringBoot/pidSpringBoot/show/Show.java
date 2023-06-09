package com.pidSpringBoot.pidSpringBoot.show;

import com.github.slugify.Slugify;
import com.pidSpringBoot.pidSpringBoot.ArtistType.ArtistType;
import com.pidSpringBoot.pidSpringBoot.Representation.Representation;
import com.pidSpringBoot.pidSpringBoot.location.Location;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="shows")
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String slug;

    private String title;
    private String description;

    @Column(name = "poster_url")
    private String posterUrl;

    /**
     * Lieu de création du spectacle
     */
    @ManyToOne
    @JoinColumn(name = "location_id", nullable = true)
    private Location location;


    private boolean bookable;
    private double price;

    @OneToMany(targetEntity= Representation.class, mappedBy="show", cascade = CascadeType.ALL)
    private List<Representation> representations = new ArrayList<>();


    /**
     * Date de création du spectacle
     */
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    /**
     * Date de modification du spectacle
     */
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToMany(mappedBy = "shows",cascade = CascadeType.ALL)
    private List<ArtistType> artistTypes = new ArrayList<>();

    @Transient
    private int locationId;

    @Transient
    private int authorId;

    @Transient
    private int directorId;

    @Transient
    private int distributionId;
   
    


    public Show() {
    }

    public Show(String title, String description, String posterUrl, Location location, boolean bookable,
                double price) {
        Slugify slg = new Slugify();

        this.slug = slg.slugify(title);
        this.title = title;
        this.description = description;
        this.posterUrl = posterUrl;
        this.location = location;
        this.bookable = bookable;
        this.price = price;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = null;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int id) {
        this.locationId = id;
    }
    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int id) {
        this.authorId = id;
    }
    public int getDirectorId() {
        return directorId;
    }

    public void setDirectorId(int id) {
        this.directorId = id;
    }
    public int getDistributionId() {
        return distributionId;
    }

    public void setDistributionId(int id) {
        this.distributionId = id;
    }
    public String getSlug() {
        return slug;
    }

    private void setSlug(String slug) {
        this.slug = slug;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;

        Slugify slg = new Slugify();

        this.setSlug(slg.slugify(title));
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        if (this.location != null) {
            this.location.removeShow(this); 
        }
    
        this.location = location;
    
        if (location != null) {
            this.location.addShow(this); 
        }
    }

    public boolean isBookable() {
        return bookable;
    }

    public void setBookable(boolean bookable) {
        this.bookable = bookable;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<Representation> getRepresentations() {
        return representations;
    }

    public Show addRepresentation(Representation representation) {
        if(!this.representations.contains(representation)) {
            this.representations.add(representation);
            representation.setShow(this);
        }

        return this;
    }

    public Show removeRepresentation(Representation representation) {
        if(this.representations.contains(representation)) {
            this.representations.remove(representation);
            if(representation.getLocation().equals(this)) {
                representation.setLocation(null);
            }
        }

        return this;
    }

    @Override
    public String toString() {
        return "Show [id=" + id + ", slug=" + slug + ", title=" + title
                + ", description=" + description + ", posterUrl=" + posterUrl + ", location="
                + location + ", bookable=" + bookable + ", price=" + price
                + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
                + ", representations=" + representations.size() + "]";
    }


    public List<ArtistType> getArtistTypes() {
        return artistTypes;
    }

    public Show addArtistType(ArtistType artistType) {
        if(!this.artistTypes.contains(artistType)) {
            this.artistTypes.add(artistType);
            artistType.addShow(this);
        }

        return this;
    }

    public Show removeArtistType(ArtistType artistType) {
        if(this.artistTypes.contains(artistType)) {
            this.artistTypes.remove(artistType);
            artistType.getShows().remove(this);
        }

        return this;
    }

}




