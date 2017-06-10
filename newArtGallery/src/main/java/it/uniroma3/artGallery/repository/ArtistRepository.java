package it.uniroma3.artGallery.repository;

import org.springframework.data.repository.CrudRepository;
import it.uniroma3.artGallery.model.Artist;



public interface ArtistRepository extends CrudRepository<Artist, Long> {

    
}
