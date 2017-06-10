package it.uniroma3.artGallery.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.uniroma3.artGallery.model.Artist;
import it.uniroma3.artGallery.service.ArtistService;

@RestController
public class ArtistRestController {

		@Autowired
		ArtistService artistService;
		
	    @RequestMapping("/rest/customer/{id}")
	    public Artist getCustomer(@PathVariable Long id) {
	        return artistService.findbyId(id);
	    }
	}