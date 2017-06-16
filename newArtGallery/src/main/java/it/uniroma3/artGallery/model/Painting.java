package it.uniroma3.artGallery.model;


import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

@Entity
public class Painting {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Size(min=1)
	private String title;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy")
	@Temporal(TemporalType.DATE)
	private Date year;
	
	@NotNull
	@Size(min=1)
	
	private String technique;
	@NotNull
	@Size(min=1)
	
	private String dimension;
	
	@Lob
	private byte[] paintingPicture;
	
	@ManyToOne
	private Artist artist;
	
	@Transient
    private MultipartFile file;
	
	public Painting(){
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Date getYear() {
		return year;
	}

	public String getTechnique() {
		return technique;
	}

	public String getDimension() {
		return dimension;
	}

	public byte[] getPaintingPicture() {
		return paintingPicture;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setYear(Date year) {
		this.year = year;
	}

	public void setTechnique(String technique) {
		this.technique = technique;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public void setPaintingPicture(byte[] paintingPicture) {
		this.paintingPicture = paintingPicture;
	}
	
	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	public void setAll(Painting painting){
		this.title = painting.getTitle();
		this.year = painting.getYear();
		this.technique = painting.getTechnique();
		this.dimension = painting.getDimension();
	}
}
