package com.InkSpace.app.service;

import com.InkSpace.app.dto.EmprestimoRequestDTO;
import com.InkSpace.app.model.*;
import com.InkSpace.app.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EmprestimoService {

	private static final int DIAS_PARA_DEVOLUCAO = 14;

	@Autowired
	private ReservaService reservaService;
	@Autowired
	private ReservaRepository reservaRepository;
	@Autowired
	private StatusReservaRepository statusReservaRepository;
	@Autowired
	private EmprestimoRepository emprestimoRepository;
	@Autowired
	private LivroRepository livroRepository;
	@Autowired
	private MembroRepository membroRepository;
	@Autowired
	private MultaService multaService;

	public List<Emprestimo> findAll() {
		return emprestimoRepository.findAll();
	}

	@Transactional
	public Emprestimo realizarEmprestimo(EmprestimoRequestDTO dto) {
		Livro livro = livroRepository.findById(dto.getLivroId())
				.orElseThrow(() -> new EntityNotFoundException("Livro não encontrado!"));

		Membro membro = membroRepository.findById(dto.getMembroId())
				.orElseThrow(() -> new EntityNotFoundException("Membro não encontrado!"));

		if (livro.getCopias() <= 0) {
			throw new IllegalStateException("Livro sem cópias disponíveis no estoque!");
		}

		livro.setCopias(livro.getCopias() - 1);
		livroRepository.save(livro);

		Emprestimo novoEmprestimo = new Emprestimo();
		novoEmprestimo.setLivro(livro);
		novoEmprestimo.setMembro(membro);
		novoEmprestimo.setDataEmprestimo(LocalDate.now());

		LocalDate dataPrevista = LocalDate.now().plusDays(DIAS_PARA_DEVOLUCAO);
		novoEmprestimo.setDataPrevistaDevolucao(dataPrevista);

		return emprestimoRepository.save(novoEmprestimo);
	}

	@Transactional
	public Emprestimo realizarDevolucao(Integer emprestimoId) {
		Emprestimo emprestimo = emprestimoRepository.findById(emprestimoId)
				.orElseThrow(() -> new EntityNotFoundException("Empréstimo não encontrado!"));

		if (emprestimo.getDataDevolucao() != null) {
			throw new IllegalStateException("Este empréstimo já foi devolvido.");
		}

		emprestimo.setDataDevolucao(LocalDate.now());
		Livro livroDevolvido = emprestimo.getLivro();

		Optional<Reserva> proximaReservaOpt = reservaService.findProximaReservaAtiva(livroDevolvido.getId());

		if (proximaReservaOpt.isPresent()) {
			Reserva proximaReserva = proximaReservaOpt.get();
			StatusReserva statusAtendida = statusReservaRepository.findByDescricaoIgnoreCase("Atendida")
					.orElseThrow(() -> new EntityNotFoundException("Status 'Atendida' não encontrado."));

			proximaReserva.setStatus(statusAtendida);
			reservaRepository.save(proximaReserva);

		} else {
			livroDevolvido.setCopias(livroDevolvido.getCopias() + 1);
		}

		livroRepository.save(livroDevolvido);

		Emprestimo emprestimoSalvo = emprestimoRepository.save(emprestimo);
		multaService.verificarEGerarMulta(emprestimoSalvo);

		return emprestimoSalvo;
	}
	public List<Emprestimo> findEmprestimosAtivosPorMembro(Integer membroId) {
		return emprestimoRepository.findByMembroIdAndDataDevolucaoIsNull(membroId);
	}
}