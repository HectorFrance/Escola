package br.com.fuctura.heitor.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WelcomeController {

	private List<String> assuntos = Arrays.asList("Rest", "MVC", "API", "JSON", "Java", "Controller", "JPA");

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main(Model model) {
		model.addAttribute("message", "Olá Aluno, seja bem vindo ao curso de Spring Boot da Fuctura");
		model.addAttribute("assuntos", assuntos);

		return "Welcome"; // view
	}

	// hello?name=Bergson
	@GetMapping("/hello")
	public String mainWithParam(@RequestParam(name = "name", required = false, defaultValue = "") String name,
			Model model) {

		model.addAttribute("message", "Olá " + name + ", seja bem vindo ao curso de Spring Boot da Fuctura");
		model.addAttribute("assuntos", assuntos);

		return "Welcome"; // view
	}

}