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
import it.uniroma3.artGallery.model.Painting;
import it.uniroma3.artGallery.service.ArtistService;
import it.uniroma3.artGallery.service.PaintingService;

@Controller
public class PaintingController {
	@Autowired
	private PaintingService paintingService;
	@Autowired
	private ArtistService artistService;
	
	@GetMapping("/painting")
	public String showFormPainting(Painting painting , Model model){
		model.addAttribute("artists", this.artistService.findAll());
			return "formpainting";
	}
	
	
	@PostMapping("/painting")
	public String checkPainting(@Valid @ModelAttribute Painting painting, 
			BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "formpainting";
		}
		else {
			model.addAttribute(painting);
			paintingService.add(painting); 
		}
		return "showpainting";
	}
	
	@GetMapping("/paintingCatalog")
	public String showArtistCatalog(Painting painting , Model model) {
		model.addAttribute("paintings", this.paintingService.findAll());
		return "paintingcatalog";
	}
	
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
	
	@PostMapping("/deletePainting")
	public String deletePainting(@RequestParam("idPaintingD") Long id,Model model) {
		paintingService.delete(paintingService.findbyId(id));
		model.addAttribute("paintings", this.paintingService.findAll());
		return "paintingcatalog";
	}
}

