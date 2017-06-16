package it.uniroma3.artGallery.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.artGallery.model.Artist;
import it.uniroma3.artGallery.repository.ArtistRepository;

@Service
public class ArtistService {
	@Autowired
    private ArtistRepository artistRepository; 

    public Iterable<Artist> findAll() {
        return this.artistRepository.findAll();
    }

    @Transactional
    public void add(final Artist artist,MultipartFile file) {
    	try {
			artist.setArtistPicture(file.getBytes());
		} catch (IOException e) {
		}
        this.artistRepository.save(artist);
    }

	public Artist findbyId(Long id) {
		return this.artistRepository.findOne(id);
	}
	
	 @Transactional
	public void delete(Artist artist){
		this.artistRepository.delete(artist);
	}
	
	 @Transactional
	public Artist update(Artist artist, Long id){
		Artist a = this.artistRepository.findOne(id);
		a.setAll(artist);
		return a;
	}
}

