package com.InkSpace.app.controller;

import com.InkSpace.app.model.Autor;
import com.InkSpace.app.model.Categoria;
import com.InkSpace.app.model.Livro;
import com.InkSpace.app.repository.AutorRepository;
import com.InkSpace.app.repository.CategoriaRepository;
import com.InkSpace.app.service.LivroService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Controller
@RequestMapping("/livros")
public class LivroController {

	@Autowired
	private LivroService livroService;
	@Autowired
	private AutorRepository autorRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;

	@GetMapping
	public String redirectToLivrosLista() {
		return "redirect:/livros/lista";
	}

	@GetMapping("/novo")
	public String mostrarFormularioDeCadastro(Model model) {
		List<Autor> todosOsAutores = autorRepository.findAll();
		List<Categoria> todasAsCategorias = categoriaRepository.findAll();

		model.addAttribute("livro", new Livro());
		model.addAttribute("todosOsAutores", todosOsAutores);
		model.addAttribute("todasAsCategorias", todasAsCategorias);

		return "livros/form-livro";
	}


	@PostMapping("/salvar")
	public String salvarLivro(@ModelAttribute("livro") Livro livro, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "livros/form-livro";
		}
		livroService.save(livro);
		return "redirect:/livros/lista";
	}

	@GetMapping("/lista")
	public String listarLivros(Model model) {
		model.addAttribute("livros", livroService.findAll());
		return "livros/lista-livros";
	}

	@GetMapping("/deletar/{id}")
	public String deletarLivro(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
		livroService.deleteById(id);
		redirectAttributes.addFlashAttribute("sucesso", "Livro deletado com sucesso!");
		return "redirect:/livros/lista";
	}

	@GetMapping("/editar/{id}")
	public String mostrarFormularioDeEdicao(@PathVariable Integer id, Model model) {
		Optional<Livro> livroOptional = livroService.findById(id);

		if (livroOptional.isPresent()) {
			List<Autor> todosOsAutores = autorRepository.findAll();
			List<Categoria> todasAsCategorias = categoriaRepository.findAll();

			model.addAttribute("livro", livroOptional.get());
			model.addAttribute("todosOsAutores", todosOsAutores);
			model.addAttribute("todasAsCategorias", todasAsCategorias);

			return "livros/form-livro";
		}

		return "redirect:/livros/lista";
	}

}
