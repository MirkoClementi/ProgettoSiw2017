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
import org.springframework.web.bind.annotation.RequestMapping;
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
	@GetMapping("/insertPainting")
	public String showFormPainting(Painting painting ,Artist artist, Model model){
		List<Artist> artists = (List<Artist>) this.artistService.findAll();
		if (!artists.isEmpty()){
				model.addAttribute("artists", artists);
				return "admin/formpainting";
		}
		else{
			String stringErr ="Devi prima inserire un artista";
			model.addAttribute("artistsErr", stringErr);
			return "admin/formartist";
		}
	}
	
	@PostMapping("/insertPainting")
	public String checkPainting(@Valid @ModelAttribute Painting painting, 
			BindingResult bindingResult, Model model,@RequestParam("artist") Long id ) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("artists", this.artistService.findAll());
			return "admin/formpainting";
		}
		else {
			painting.setArtist(artistService.findbyId(id));
			model.addAttribute(painting);
			model.addAttribute("immagine",painting.getFile());
			paintingService.add(painting,painting.getFile()); 
		}
		return "admin/showpainting";
	}
	
	@PostMapping("showPainting")
	public String showPainting(@RequestParam("id") Long id, Model model){
			model.addAttribute(paintingService.findbyId(id));
		return "admin/showpainting";
	}
//-------------------------------------------------------------------------------
	
//Lista di tutti i quadri -------------------------------------------------------
	@GetMapping("/paintingCatalog")
	public String showArtistCatalog(Painting painting , Model model) {
		model.addAttribute("paintings", this.paintingService.findAll());
		return "admin/paintingcatalog";
	}
//--------------------------------------------------------------------------------
	
//Aggiornamento dati quadro ------------------------------------------------------
	@GetMapping("/updatePainting")
	public String showFormUpdatePainting(@RequestParam("idPainting") Long id,Painting painting,Model model) {
		model.addAttribute(paintingService.findbyId(id));
		return "admin/formupdatepainting";
	}
	
	@PostMapping("/updatePainting")
	public String updatePainting(@Valid @ModelAttribute Painting painting,@RequestParam("idPainting") Long id, 
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "admin/formupdatepainting";
		}
		else {
			model.addAttribute(paintingService.update(painting, id));
		}
		return "admin/showpainting";
	}
//---------------------------------------------------------------------------------
	
//Cancellazione quadro ------------------------------------------------------------
	@PostMapping("/deletePainting")
	public String deletePainting(@RequestParam("idPaintingD") Long id,Model model) {
		paintingService.delete(paintingService.findbyId(id));
		model.addAttribute("paintings", this.paintingService.findAll());
		return "admin/paintingcatalog";
	}
	

	@RequestMapping("/painting")
	public String painting(Model model) {
		model.addAttribute("paintings", this.paintingService.findAll());
		return "nag/painting";
	}
}

