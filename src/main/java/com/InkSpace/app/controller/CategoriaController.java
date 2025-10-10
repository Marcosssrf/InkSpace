package com.InkSpace.app.controller;

import com.InkSpace.app.model.Categoria;
import com.InkSpace.app.repository.CategoriaRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@GetMapping("/novo")
	public String mostrarFormularioDeCadastro(Model model) {
		model.addAttribute("categoria", new Categoria());
		return "categorias/form-categoria";
	}

	@PostMapping("/salvar")
	public String salvarCategoria(@Valid @ModelAttribute("categoria") Categoria categoria, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return "categorias/form-categoria";
		}

		categoriaRepository.save(categoria);
		redirectAttributes.addFlashAttribute("sucesso", "Categoria salva com sucesso!");
		return "redirect:/categorias/lista";
	}

	@GetMapping("/lista")
	public String listarCategorias(Model model) {
		model.addAttribute("categorias", categoriaRepository.findAll());
		return "categorias/lista-categoria";
	}

	@GetMapping("/editar/{id}")
	public String mostrarFormularioDeEdicao(@PathVariable Integer id, Model model) {
		Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);
		if (categoriaOptional.isPresent()) {
			model.addAttribute("categoria", categoriaOptional.get());
			return "categorias/form-categoria";
		}
		return "redirect:/categorias/lista";
	}

	@GetMapping("/deletar/{id}")
	public String deletarCategoria(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
		try {
			categoriaRepository.deleteById(id);
			redirectAttributes.addFlashAttribute("sucesso", "Categoria deletada com sucesso!");
		} catch (DataIntegrityViolationException e) {
			redirectAttributes.addFlashAttribute("erro", "Não é possível deletar esta categoria, pois ela está associada a um ou mais livros.");
		}
		return "redirect:/categorias/lista";
	}

}
