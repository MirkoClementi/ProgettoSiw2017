package it.uniroma3.artGallery.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import it.uniroma3.artGallery.model.Painting;

public interface PaintingRepository extends CrudRepository<Painting, Long> {

    List<Painting> findByTitle(String title);
    
}