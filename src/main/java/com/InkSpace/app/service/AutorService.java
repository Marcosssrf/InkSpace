package com.InkSpace.app.service;

import com.InkSpace.app.model.Autor;
import com.InkSpace.app.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {

	@Autowired
	private AutorRepository autorRepository;


	public List<Autor> findAll() {
		return autorRepository.findAll();
	}

	public Autor save(Autor autor) {
		return autorRepository.save(autor);
	}

	public Optional<Autor> findById(Integer id) {
		return autorRepository.findById(id);
	}

	public void deleteById(Integer id) {
		autorRepository.deleteById(id);
	}

	public List<Autor> saveAll(List<Autor> autores) {
		return autorRepository.saveAll(autores);
	}

}
