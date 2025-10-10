package com.InkSpace.app.service;

import com.InkSpace.app.model.Emprestimo;
import com.InkSpace.app.model.Multa;
import com.InkSpace.app.model.MultaStatus;
import com.InkSpace.app.model.PagamentoMulta;
import com.InkSpace.app.repository.MultaRepository;
import com.InkSpace.app.repository.PagamentoMultaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class MultaService {

	private static final BigDecimal VALOR_MULTA_POR_DIA = new BigDecimal("0.50");

	@Autowired
	private MultaRepository multaRepository;
	@Autowired
	private PagamentoMultaRepository pagamentoMultaRepository;

	public List<Multa> findAll() {
		return multaRepository.findAll();
	}

	@Transactional
	public void verificarEGerarMulta(Emprestimo emprestimo) {
		if (emprestimo.getDataDevolucao() == null || !emprestimo.getDataDevolucao().isAfter(emprestimo.getDataPrevistaDevolucao())) {
			return;
		}

		long diasDeAtraso = ChronoUnit.DAYS.between(emprestimo.getDataPrevistaDevolucao(), emprestimo.getDataDevolucao());
		if (diasDeAtraso > 0) {
			BigDecimal valorDaMulta = VALOR_MULTA_POR_DIA.multiply(new BigDecimal(diasDeAtraso));

			Multa novaMulta = new Multa();
			novaMulta.setEmprestimo(emprestimo);
			novaMulta.setDataMulta(LocalDate.now());
			novaMulta.setValorMulta(valorDaMulta);
			novaMulta.setStatus(MultaStatus.PENDENTE);

			multaRepository.save(novaMulta);
		}
	}

	@Transactional
	public void pagarMulta(Integer multaId) {
		Multa multa = multaRepository.findById(multaId)
				.orElseThrow(() -> new EntityNotFoundException("Multa não encontrada!"));

		if (multa.getStatus() == MultaStatus.PAGA) {
			throw new IllegalStateException("Esta multa já foi paga.");
		}

		PagamentoMulta pagamento = new PagamentoMulta();
		pagamento.setMulta(multa);
		pagamento.setMembro(multa.getEmprestimo().getMembro());
		pagamento.setDataPagamento(LocalDate.now());
		pagamento.setValorPagamento(multa.getValorMulta());
		pagamentoMultaRepository.save(pagamento);

		multa.setStatus(MultaStatus.PAGA);
		multaRepository.save(multa);
	}
}