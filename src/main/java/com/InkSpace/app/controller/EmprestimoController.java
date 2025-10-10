package com.InkSpace.app.controller;

import com.InkSpace.app.dto.EmprestimoRequestDTO;
import com.InkSpace.app.model.Emprestimo;
import com.InkSpace.app.model.Livro;
import com.InkSpace.app.model.Membro;
import com.InkSpace.app.repository.LivroRepository;
import com.InkSpace.app.repository.MembroRepository;
import com.InkSpace.app.service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/emprestimos")
public class EmprestimoController {

	@Autowired
	private EmprestimoService emprestimoService;
	@Autowired
	private LivroRepository livroRepository;
	@Autowired
	private MembroRepository membroRepository;

	@GetMapping("/lista")
	public String listarEmprestimos(@RequestParam(name = "membroId", required = false) Integer membroId, Model model) {

		List<Emprestimo> emprestimos;

		if (membroId != null && membroId > 0) {
			emprestimos = emprestimoService.findEmprestimosAtivosPorMembro(membroId);
		} else {
			emprestimos = emprestimoService.findAll();
		}

		model.addAttribute("todosOsMembros", membroRepository.findAll());
		// ------------------------------------

		model.addAttribute("emprestimos", emprestimos);
		model.addAttribute("membroIdSelecionado", membroId);

		return "emprestimos/lista-emprestimos";
	}

	@GetMapping("/novo")
	public String mostrarFormularioDeEmprestimo(Model model) {
		// Busca apenas livros com cópias > 0
		List<Livro> livrosDisponiveis = livroRepository.findByCopiasGreaterThan(0);
		List<Membro> todosOsMembros = membroRepository.findAll();

		model.addAttribute("emprestimoDto", new EmprestimoRequestDTO());
		model.addAttribute("livrosDisponiveis", livrosDisponiveis);
		model.addAttribute("todosOsMembros", todosOsMembros);

		return "emprestimos/form-emprestimo";
	}

	@PostMapping("/salvar")
	public String salvarEmprestimo(@ModelAttribute("emprestimoDto") EmprestimoRequestDTO dto, RedirectAttributes redirectAttributes) {
		try {
			emprestimoService.realizarEmprestimo(dto);
			redirectAttributes.addFlashAttribute("sucesso", "Empréstimo realizado com sucesso!");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("erro", e.getMessage());
			return "redirect:/emprestimos/novo";
		}
		return "redirect:/emprestimos/lista";
	}

	@GetMapping("/devolver/{id}")
	public String devolverLivro(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
		try {
			emprestimoService.realizarDevolucao(id);
			redirectAttributes.addFlashAttribute("sucesso", "Devolução registrada com sucesso!");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("erro", e.getMessage());
		}
		return "redirect:/emprestimos/lista";
	}
}