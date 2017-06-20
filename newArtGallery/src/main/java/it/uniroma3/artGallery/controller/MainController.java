package it.uniroma3.artGallery.controller;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {


	//Welcome 
	@RequestMapping("/")
	public String welcome(){
		return "welcome";
	}

	//Welcome 
	@RequestMapping("/index")
	public String index(){
		return "index";
	}

	// Login form
	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/user")
	public Principal user(Principal principal) {
		return principal;
	}

	// Login form with error
	@RequestMapping("/login-error.html")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "login";
	}

	@RequestMapping("/adminPanel")
	public String adminPanel() {
		return "admin/adminpanel";
	}

	@RequestMapping("/admin")
	public String adminLogin() {
		return "admin/adminPanel";
	}

	@RequestMapping(value="403")
	public String Error403(){
		return "403";
	}

}
