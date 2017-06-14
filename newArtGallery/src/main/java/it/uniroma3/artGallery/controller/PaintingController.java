package it.uniroma3.artGallery.controller;

import java.util.List;

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
import it.uniroma3.artGallery.model.Painting;
import it.uniroma3.artGallery.service.ArtistService;
import it.uniroma3.artGallery.service.PaintingService;

@Controller
public class PaintingController {
	@Autowired
	private PaintingService paintingService;
	@Autowired
	private ArtistService artistService;
	
// Inserimento nuovo quadro ----------------------------------------------------
	@GetMapping("/painting")
	public String showFormPainting(Painting painting ,Artist artist, Model model){
		List<Artist> artists = (List<Artist>) this.artistService.findAll();
		if (!artists.isEmpty()){
				model.addAttribute("artists", artists);
				return "formpainting";
		}
		else{
			String stringErr ="Devi prima inserire un artista";
			model.addAttribute("artistsErr", stringErr);
			return "formartist";
		}
	}
	
	@PostMapping("/painting")
	public String checkPainting(@Valid @ModelAttribute Painting painting, 
			BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("artists", this.artistService.findAll());
			return "formpainting";
		}
		else {
			model.addAttribute(painting);
			paintingService.add(painting); 
		}
		return "showpainting";
	}
//-------------------------------------------------------------------------------
	
//Lista di tutti i quadri -------------------------------------------------------
	@GetMapping("/paintingCatalog")
	public String showArtistCatalog(Painting painting , Model model) {
		model.addAttribute("paintings", this.paintingService.findAll());
		return "paintingcatalog";
	}
//--------------------------------------------------------------------------------
	
//Aggiornamento dati quadro ------------------------------------------------------
	@GetMapping("/updatePainting")
	public String showFormUpdatePainting(@RequestParam("idPainting") Long id,Painting painting,Model model) {
		model.addAttribute(paintingService.findbyId(id));
		return "formupdatepainting";
	}
	
	@PostMapping("/updatePainting")
	public String updatePainting(@Valid @ModelAttribute Painting painting,@RequestParam("idPainting") Long id, 
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "formupdatepainting";
		}
		else {
			model.addAttribute(paintingService.update(painting, id));
		}
		return "showpainting";
	}
//---------------------------------------------------------------------------------
	
//Cancellazione quadro ------------------------------------------------------------
	@PostMapping("/deletePainting")
	public String deletePainting(@RequestParam("idPaintingD") Long id,Model model) {
		paintingService.delete(paintingService.findbyId(id));
		model.addAttribute("paintings", this.paintingService.findAll());
		return "paintingcatalog";
	}
}

