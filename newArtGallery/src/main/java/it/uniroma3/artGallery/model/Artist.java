package it.uniroma3.artGallery.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.CascadeType;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

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
	
	@Lob
	private byte[] artistPicture;
	
	@OneToMany(mappedBy="artist" , cascade= {CascadeType.REMOVE},fetch = FetchType.EAGER)
	private List<Painting> paintings;
	
	@Transient
    private MultipartFile file;
	
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

	public byte[] getArtistPicture() {
		return artistPicture;
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

	public void setArtistPicture(byte[] artistPicture) {
		this.artistPicture = artistPicture;
	}

	public void setPaintings(List<Painting> paintings) {
		this.paintings = paintings;
	}
	
	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	public void setAll(Artist artist){
		this.firstName = artist.getFirstName();
		this.lastName = artist.getLastName();
		this.nationality = artist.getNationality();
		this.birthDate = artist.getBirthDate();
		this.deadDate = artist.getDeadDate();
	}
}