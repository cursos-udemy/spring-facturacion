package com.jendrix.udemy.facturacion.app.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

	@GetMapping("/login")
	private String login(@RequestParam(value = "error", required = false) String error, Model model, Principal principal,
			RedirectAttributes flash) {
		if (principal != null) {
			flash.addFlashAttribute("info", "El usuario ya ha iniciado session!");
			return "redirect:/";
		}

		model.addAttribute("title", "Jendrix Software!!!");
		if (error != null) {
			model.addAttribute("error", "Username o Password incorrecto!");
		}

		return "login";
	}

}
