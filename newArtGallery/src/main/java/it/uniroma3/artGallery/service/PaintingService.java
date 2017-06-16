package it.uniroma3.artGallery.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import it.uniroma3.artGallery.model.Painting;
import it.uniroma3.artGallery.repository.PaintingRepository;

@Service
public class PaintingService {
	@Autowired
	private PaintingRepository paintingRepository; 

	public Iterable<Painting> findAll() {
		return this.paintingRepository.findAll();
	}

	@Transactional
	public void add(final Painting painting, MultipartFile file) {
		try {
			painting.setPaintingPicture(file.getBytes());
		} catch (IOException e) {
		}
		this.paintingRepository.save(painting);
	}

	public Painting findbyId(Long id) {
		return this.paintingRepository.findOne(id);
	}

	@Transactional
	public void delete(Painting painting){
		this.paintingRepository.delete(painting);
	}

	@Transactional
	public Painting update(Painting painting, Long id){
		Painting p = this.paintingRepository.findOne(id);
		p.setAll(painting);
		return p;
	}
}
