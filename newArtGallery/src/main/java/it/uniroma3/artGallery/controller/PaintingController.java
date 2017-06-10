package it.uniroma3.artGallery.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
	public String showFormPainting(Painting painting){
		//model.addAttribute(this.artistService.findAll());
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
}

