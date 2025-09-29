package com.InkSpace.app.service;

import com.InkSpace.app.model.Categoria;
import com.InkSpace.app.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

	private final CategoriaRepository categoriaRepository;

	public CategoriaService(CategoriaRepository categoriaRepository) {
		this.categoriaRepository = categoriaRepository;
	}

	//GET
	public List<Categoria> getAll(){
		return categoriaRepository.findAll();
	}

	//POST
	public Categoria save(Categoria categoria){
		return categoriaRepository.save(categoria);
	}

	//DELETE
	public void delete(Integer id){
		categoriaRepository.deleteById(id);
	}

}
