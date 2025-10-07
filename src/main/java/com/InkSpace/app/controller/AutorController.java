package com.InkSpace.app.controller;

import com.InkSpace.app.model.Autor;
import com.InkSpace.app.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;


@Controller
@RequestMapping("/autores")
public class AutorController {

	@Autowired
	private AutorService autorService;

	@GetMapping("/novo")
	public String mostrarFormularioDeCadastro(Model model) {
		model.addAttribute("autor", new Autor());
		return "autores/form-autor";
	}

	@PostMapping("/salvar")
	public String salvarMembro(@ModelAttribute("autor") Autor autor) {
		autorService.save(autor);
		return "redirect:/autores/lista";
	}

	@GetMapping("/lista")
	public String listarAutores(Model model) {
		model.addAttribute("autores", autorService.findAll());
		return "autores/lista-autores";
	}

	@GetMapping("/deletar/{id}")
	public String deletarAutores(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
		autorService.deleteById(id);
		redirectAttributes.addFlashAttribute("sucesso", "Autor deletado com sucesso!");
		return "redirect:/autores/lista";
	}

	@GetMapping("/editar/{id}")
	public String mostrarFormularioDeEdicao(@PathVariable Integer id, Model model) {
		Optional<Autor> autorOptional = autorService.findById(id);
		if (autorOptional.isPresent()) {
			model.addAttribute("autor", autorOptional.get());
			return "autores/form-autor";
		}

		return "redirect:/autores/lista";
	}
}


