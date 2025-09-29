package com.InkSpace.app.service;

import com.InkSpace.app.model.Membro;
import com.InkSpace.app.repository.MembroRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembroService {

	private final MembroRepository membroRepository;

	public MembroService(MembroRepository membroRepository) {
		this.membroRepository = membroRepository;
	}

	//LISTAR
	public List<Membro> getAll() {
		return membroRepository.findAll();
	}

	//CRIAR
	public Membro save(Membro membro) {
		return membroRepository.save(membro);
	}

	//DELETAR
	public void delete(Integer id) {
		membroRepository.deleteById(id);
	}

	public Membro findPorId(Integer id) {
		return membroRepository.findPorId(id);
	}

	public Membro update(Membro membro, Integer id) {
		Membro membro1 = findPorId(id);
		if (membro1 == null) {
			throw new EntityNotFoundException("Usuario não encontrado!");
		}
		if (membro.getNome() != null){
			membro1.setNome(membro.getNome());
		}
		if(membro.getSobrenome() != null){
			membro1.setSobrenome(membro.getSobrenome());
		}
		if(membro.getStatusId() != null){
			membro1.setStatusId(membro.getStatusId());
		}
		return membroRepository.save(membro1);
	}
}
