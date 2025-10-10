package com.InkSpace.app.repository;
import com.InkSpace.app.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
	Optional<Reserva> findFirstByLivroIdAndStatus_DescricaoOrderByDataReservaAsc(Integer livroId, String statusDescricao);

	boolean existsByLivroIdAndMembroIdAndStatus_Descricao(Integer livroId, Integer membroId, String statusDescricao);
}