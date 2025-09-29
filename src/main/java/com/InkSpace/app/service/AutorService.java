package com.InkSpace.app.service;

import com.InkSpace.app.model.Autor;
import com.InkSpace.app.repository.AutorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AutorService {

	private final AutorRepository autorRepository;

	public AutorService(AutorRepository autorRepository) {
		this.autorRepository = autorRepository;
	}

	//GET
	public List<Autor> getAll(){
		return autorRepository.findAll();
	}
	//POST
	public Autor save(Autor autor){
		return autorRepository.save(autor);
	}
	//DELETE
	public void delete(Integer id){
		autorRepository.deleteById(id);
	}
	//UPDATE
	public Autor findPorId(Integer id) {
		return autorRepository.findPorId(id);
	}

	public Autor update(Autor autor, Integer id) {
		Autor autor1 = findPorId(id);
		if (autor1 == null) {
			throw new EntityNotFoundException("Usuario não encontrado!");
		}
		if (autor.getNome() != null){
			autor1.setNome(autor.getNome());
		}
		if(autor.getSobrenome() != null){
			autor1.setSobrenome(autor.getSobrenome());
		}
		return autorRepository.save(autor1);
	}

}
