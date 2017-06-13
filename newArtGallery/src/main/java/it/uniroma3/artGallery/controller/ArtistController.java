package it.uniroma3.artGallery.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.artGallery.model.Artist;
import it.uniroma3.artGallery.service.ArtistService;

@Controller
public class ArtistController {
	@Autowired
	private ArtistService artistService;

// Inserimento nuovo artista -------------------------------------------------
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
//-------------------------------------------------------------------------------

//Lista di tutti gli artisti ----------------------------------------------------
	@GetMapping("/artistCatalog")
	public String showArtistCatalog(Artist artist , Model model) {
		model.addAttribute("artists", this.artistService.findAll());
		return "artistcatalog";
	}
//-------------------------------------------------------------------------------
	
//Aggiornamento dati artista ----------------------------------------------------
	@GetMapping("/updateArtist")
	public String showFormUpdateArtist(@RequestParam("idArtist") Long id,Artist artist,Model model) {
		model.addAttribute(artistService.findbyId(id));
		return "formupdateartist";
	}
	
	@PostMapping("/updateArtist")
	public String updateArtist(@Valid @ModelAttribute Artist artist,@RequestParam("idArtist") Long id, 
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "formupdateartist";
		}
		else {
			model.addAttribute(artistService.update(artist,id));
		}
		return "showartist";
	}
//----------------------------------------------------------------------------------
	
//Cancellazione artista ------------------------------------------------------------
	@PostMapping("/deleteArtist")
	public String deleteArtist(@RequestParam("idArtistD") Long id,Model model) {
		artistService.delete(artistService.findbyId(id));
		model.addAttribute("artists", this.artistService.findAll());
		return "artistcatalog";
	}
//-----------------------------------------------------------------------------------
}
