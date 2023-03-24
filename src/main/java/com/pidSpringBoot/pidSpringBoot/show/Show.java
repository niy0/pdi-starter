package com.pidSpringBoot.pidSpringBoot.show;

import java.time.LocalDateTime;

import com.github.slugify.Slugify;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name= "shows")
public class Show {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(unique=true)
	private String slug;
	
	private String title;
	private String description;
	
	@Column(name="poster_url")
	private String posterUrl;
	
	
	
//	@ManyToOne
//	@JoinColumn(name="locality_id", nullable=true)
//	private Location location;

	

	private boolean bookable;
	private double price;
	
	@Column(name="created_at")
	private LocalDateTime createdAt;
	
	@Column(name="updated_at")
	private LocalDateTime updatedAt;
	
	public Show() {}

	public Show( String title, String description, String posterUrl, boolean bookable,
			double price) {
		Slugify slg =Slugify.builder().build();
		this.slug = slg.slugify(title);
		this.id = id;
		this.slug = slug;
		this.title = title;
		this.description = description;
		this.posterUrl = posterUrl;
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

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
		
		Slugify slg =Slugify.builder().build();
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
//	
//	public Location getLocation() {
//		return location;
//	}
//	
//	public void setLocation(Location location) {
//		this.location.removeShow(this);
//		this.location = location;
//		this.getLocation().addShow(this)
//	}

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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	@Override
	public String toString() {
		return "Show [id=" + id + ", slug=" + slug + ", title=" + title + ", description=" + description
				+ ", posterUrl=" + posterUrl + ", bookable=" + bookable + ", price=" + price + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + "]";
	}
	
	
	
}
