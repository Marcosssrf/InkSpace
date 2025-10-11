package com.InkSpace.app.repository;

import com.InkSpace.app.dto.LivroEstatisticaDTO;
import com.InkSpace.app.model.Emprestimo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Integer> {
	List<Emprestimo> findByMembroIdAndDataDevolucaoIsNull(Integer membroId);
	boolean existsByLivroIdAndMembroIdAndDataDevolucaoIsNull(Integer livroId, Integer membroId);
	@Query("SELECT new com.InkSpace.app.dto.LivroEstatisticaDTO(e.livro, COUNT(e.livro)) " +
			"FROM Emprestimo e " +
			"GROUP BY e.livro " +
			"ORDER BY COUNT(e.livro) DESC")
	List<LivroEstatisticaDTO> findLivrosMaisEmprestados(Pageable pageable);
}
