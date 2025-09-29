package com.InkSpace.app.controller;

import com.InkSpace.app.model.Categoria;
import com.InkSpace.app.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaController {

	private final CategoriaService categoriaService;

	@GetMapping
	public ResponseEntity<List<Categoria>> getAll(){
		List<Categoria> categorias = new ArrayList<>();
		categorias = categoriaService.getAll();
		return ResponseEntity.ok(categorias);
	}

	@PostMapping
	public ResponseEntity<Categoria> create(@RequestBody Categoria categorias){
		categoriaService.save(categorias);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categorias.getId()).toUri();
		return ResponseEntity.created(uri).body(categorias);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id){
		categoriaService.delete(id);
	}

}
