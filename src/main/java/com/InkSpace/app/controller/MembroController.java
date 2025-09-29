package com.InkSpace.app.controller;
import com.InkSpace.app.model.Membro;
import com.InkSpace.app.service.MembroService;
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
@RequestMapping("/membros")
@RequiredArgsConstructor
public class MembroController {

	private final MembroService membroService;

	@GetMapping
	public ResponseEntity<List<Membro>> getAll() {
		List<Membro> membros = new ArrayList<>();
		membros = membroService.getAll();
		return ResponseEntity.ok(membros);
	}

//	@GetMapping("/novo")
//	public String getAll() {
//		List<Membro> membros = new ArrayList<>();
//		membros = membroService.getAll();
//		return "membros/form-membro";
//	}

//	@GetMapping("/novo")
//	public String mostrarFormularioDeCadastro(Model model) {
//		model.addAttribute("membro", new Membro());
//		return "membros/form-membro";
//	}

	@PostMapping
	public ResponseEntity<Membro> create(@RequestBody Membro membro){
		membroService.save(membro);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(membro.getId()).toUri();
		return ResponseEntity.created(uri).body(membro);
	}

//	@PostMapping("/novo")
//	public String create(@RequestBody Membro membro){
//		membroService.save(membro);
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(membro.getId()).toUri();
//		return "membros/form-membro";
//	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id){
		membroService.delete(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Membro> update(@PathVariable Integer id, @RequestBody Membro membro){
		Membro membro1 = membroService.update(membro, id);
		return ResponseEntity.ok(membro1);
	}

}


