package com.InkSpace.app.service;

import com.InkSpace.app.model.Categoria;
import com.InkSpace.app.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public List<Categoria> findAll(){
		return categoriaRepository.findAll();
	}

	public Categoria save(Categoria categoria){
		return categoriaRepository.save(categoria);
	}

	public Optional<Categoria> findById(Integer id) {
		return categoriaRepository.findById(id);
	}

	public void delete(Integer id){
		categoriaRepository.deleteById(id);
	}

	public List<Categoria> saveAll(List<Categoria> categorias) {
		return categoriaRepository.saveAll(categorias);
	}

}
