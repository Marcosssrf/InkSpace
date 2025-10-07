package com.InkSpace.app.controller;

import com.InkSpace.app.model.Autor;
import com.InkSpace.app.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/autores")
public class AutorApiController {

	@Autowired
	private AutorService autorService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Autor criarAutor(@RequestBody Autor autor) {
		return autorService.save(autor);
	}

	@PostMapping("/batch")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Autor> criarVariosAutores(@RequestBody List<Autor> autores) {
		return autorService.saveAll(autores);
	}
}