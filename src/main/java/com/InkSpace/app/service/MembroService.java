package com.InkSpace.app.service;

import com.InkSpace.app.model.Membro;
import com.InkSpace.app.repository.MembroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MembroService {

	@Autowired
	private MembroRepository membroRepository;

	public List<Membro> findAll() {
		return membroRepository.findAll();
	}

	public Membro save(Membro membro) {
		return membroRepository.save(membro);
	}

	public Optional<Membro> findById(Integer id) {
		return membroRepository.findById(id);
	}

	public void deleteById(Integer id) {
		membroRepository.deleteById(id);
	}
}
