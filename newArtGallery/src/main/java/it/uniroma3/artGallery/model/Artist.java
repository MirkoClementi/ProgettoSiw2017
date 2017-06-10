package it.uniroma3.artGallery.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Artist {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Size(min=1)
	private String firstName;
	
	@NotNull
	@Size(min=1)
	private String lastName;
	
	@NotNull
	@Size(min=1)
	private String nationality;
	
	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date birthDate;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date deadDate;
	
	private String picture;
	
	@OneToMany(mappedBy="artist")
	private List<Painting> paintings;
	
	public Artist(){
		this.paintings = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}	


	public String getNationality() {
		return nationality;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public Date getDeadDate() {
		return deadDate;
	}

	public String getPicture() {
		return picture;
	}

	public List<Painting> getPaintings() {
		return paintings;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public void setDeadDate(Date deadDate) {
		this.deadDate = deadDate;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public void setPaintings(List<Painting> paintings) {
		this.paintings = paintings;
	}

}