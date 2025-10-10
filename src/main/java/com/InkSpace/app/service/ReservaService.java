package com.InkSpace.app.service;

import com.InkSpace.app.model.*;
import com.InkSpace.app.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ReservaService {

	@Autowired
	private ReservaRepository reservaRepository;
	@Autowired
	private LivroRepository livroRepository;
	@Autowired
	private MembroRepository membroRepository;
	@Autowired
	private StatusReservaRepository statusReservaRepository;
	@Autowired
	private EmprestimoRepository emprestimoRepository;

	private static final int DIAS_PARA_DEVOLUCAO = 14;


	@Transactional
	public Reserva criarReserva(Integer livroId, Integer membroId) {
		Livro livro = livroRepository.findById(livroId)
				.orElseThrow(() -> new EntityNotFoundException("Livro não encontrado."));

		Membro membro = membroRepository.findById(membroId)
				.orElseThrow(() -> new EntityNotFoundException("Membro não encontrado."));

		if (livro.getCopias() > 0) {
			throw new IllegalStateException("Não é possível reservar um livro com cópias disponíveis em estoque.");
		}
		if (emprestimoRepository.existsByLivroIdAndMembroIdAndDataDevolucaoIsNull(livroId, membroId)) {
			throw new IllegalStateException("Você não pode reservar um livro que já está emprestado para você.");
		}
		if (reservaRepository.existsByLivroIdAndMembroIdAndStatus_Descricao(livroId, membroId, "ATIVA")) {
			throw new IllegalStateException("Você já possui uma reserva ativa para este livro.");
		}

		StatusReserva statusAtiva = statusReservaRepository.findByDescricaoIgnoreCase("ATIVA")
				.orElseThrow(() -> new EntityNotFoundException("Status de reserva 'ATIVA' não encontrado."));

		Reserva novaReserva = new Reserva();
		novaReserva.setLivro(livro);
		novaReserva.setMembro(membro);
		novaReserva.setDataReserva(LocalDate.now());
		novaReserva.setStatus(statusAtiva);

		return reservaRepository.save(novaReserva);
	}

	public Optional<Reserva> findProximaReservaAtiva(Integer livroId) {
		return reservaRepository.findFirstByLivroIdAndStatus_DescricaoOrderByDataReservaAsc(livroId, "ATIVA");
	}

	@Transactional
	public Emprestimo finalizarReservaComEmprestimo(Integer reservaId) {
		Reserva reserva = reservaRepository.findById(reservaId)
				.orElseThrow(() -> new EntityNotFoundException("Reserva não encontrada!"));

		if (!reserva.getStatus().getDescricao().equalsIgnoreCase("ATENDIDA")) {
			throw new IllegalStateException("Esta reserva não pode ser convertida em empréstimo. Status atual: " + reserva.getStatus().getDescricao());
		}

		Emprestimo novoEmprestimo = new Emprestimo();
		novoEmprestimo.setLivro(reserva.getLivro());
		novoEmprestimo.setMembro(reserva.getMembro());
		novoEmprestimo.setDataEmprestimo(LocalDate.now());
		LocalDate dataPrevista = LocalDate.now().plusDays(DIAS_PARA_DEVOLUCAO);
		novoEmprestimo.setDataPrevistaDevolucao(dataPrevista);
		emprestimoRepository.save(novoEmprestimo);

		StatusReserva statusFinalizada = statusReservaRepository.findByDescricaoIgnoreCase("FINALIZADA")
				.orElseThrow(() -> new EntityNotFoundException("Status 'FINALIZADA' não encontrado."));
		reserva.setStatus(statusFinalizada);
		reservaRepository.save(reserva);

		return novoEmprestimo;
	}

}