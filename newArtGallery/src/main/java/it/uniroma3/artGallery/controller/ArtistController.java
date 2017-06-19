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
	@GetMapping("/insertArtist")
	public String showFormArtist(Artist artist) {
		return "admin/formartist";
	}

	@PostMapping("/insertArtist")
	public String checkArtist(@Valid @ModelAttribute Artist artist, 
			BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "admin/formartist";
		}
		else {
			model.addAttribute(artist);
			model.addAttribute("immagine",artist.getFile());
			artistService.add(artist,artist.getFile()); 
		}
		return "admin/showartist";
	}
	
	@PostMapping("showArtist")
	public String showArtist(@RequestParam("id") Long id, Model model){
			model.addAttribute(artistService.findbyId(id));
		return "admin/showartist";
	}
//-------------------------------------------------------------------------------

//Lista di tutti gli artisti ----------------------------------------------------
	@GetMapping("/artistCatalog")
	public String showArtistCatalog(Artist artist , Model model) {
		model.addAttribute("artists", this.artistService.findAll());
		return "admin/artistcatalog";
	}
//-------------------------------------------------------------------------------
	
//Aggiornamento dati artista ----------------------------------------------------
	@GetMapping("/updateArtist")
	public String showFormUpdateArtist(@RequestParam("idArtist") Long id,Artist artist,Model model) {
		model.addAttribute(artistService.findbyId(id));
		return "admin/formupdateartist";
	}
	
	@PostMapping("/updateArtist")
	public String updateArtist(@Valid @ModelAttribute Artist artist,@RequestParam("idArtist") Long id, 
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "admin/formupdateartist";
		}
		else {
			model.addAttribute(artistService.update(artist,id));
		}
		return "admin/showartist";
	}
//----------------------------------------------------------------------------------
	
//Cancellazione artista ------------------------------------------------------------
	@PostMapping("/deleteArtist")
	public String deleteArtist(@RequestParam("idArtistD") Long id,Model model) {
		artistService.delete(artistService.findbyId(id));
		model.addAttribute("artists", this.artistService.findAll());
		return "admin/artistcatalog";
	}
//-----------------------------------------------------------------------------------
}
