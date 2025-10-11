package com.InkSpace.app.controller;

import com.InkSpace.app.service.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/relatorios")
public class RelatorioController {

	@Autowired
	private RelatorioService relatorioService;

	@GetMapping
	public String paginaDeRelatorios(Model model) {
		model.addAttribute("topLivros", relatorioService.findTop10LivrosMaisEmprestados());
		return "relatorios/lista-relatorios";
	}
}