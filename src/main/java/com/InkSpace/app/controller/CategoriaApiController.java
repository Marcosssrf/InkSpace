package com.InkSpace.app.controller;

import com.InkSpace.app.model.Categoria;
import com.InkSpace.app.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaApiController {

	@Autowired
	private CategoriaService categoriaService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Categoria criarCategoria(@RequestBody Categoria categoria) {
		return categoriaService.save(categoria);
	}

	@GetMapping
	public List<Categoria> listarTodasCategorias() {
		return categoriaService.findAll();
	}

	@PostMapping("/batch")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Categoria> criarVariasCategorias(@RequestBody List<Categoria> categorias) {
		return categoriaService.saveAll(categorias);
	}

}