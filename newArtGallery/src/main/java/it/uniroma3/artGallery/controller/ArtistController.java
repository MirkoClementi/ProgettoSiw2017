package it.uniroma3.artGallery.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import it.uniroma3.artGallery.model.Artist;
import it.uniroma3.artGallery.service.ArtistService;

@Controller
public class ArtistController {
	@Autowired
	private ArtistService artistService;

	@GetMapping("/artist")
	public String showFormArtist(Artist artist) {
		return "formartist";
	}

	@PostMapping("/artist")
	public String checkArtist(@Valid @ModelAttribute Artist artist, 
			BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "formartist";
		}
		else {
			model.addAttribute(artist);
			artistService.add(artist); 
		}
		return "showartist";
	}
	
	@GetMapping("/artistCatalog")
	public String showArtistCatalog(Artist artist , Model model) {
		model.addAttribute("artists", this.artistService.findAll());
		return "artistcatalog";
	}
	
	@PostMapping("/updateArtist")
	public String updateArtist() {
		//TODO
		return "showartist";
	}
	
	@PostMapping("/deleteArtist")
	public String deleteArtist() {
		//TODO
		return "showartist";
	}
}
