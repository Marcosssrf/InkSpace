package com.InkSpace.app.service;

import com.InkSpace.app.dto.LivroEstatisticaDTO;
import com.InkSpace.app.repository.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelatorioService {

	@Autowired
	private EmprestimoRepository emprestimoRepository;

	public List<LivroEstatisticaDTO> findTop10LivrosMaisEmprestados() {
		Pageable topTen = PageRequest.of(0, 10);
		return emprestimoRepository.findLivrosMaisEmprestados(topTen);
	}
}