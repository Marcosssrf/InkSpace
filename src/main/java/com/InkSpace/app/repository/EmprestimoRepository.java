package com.InkSpace.app.repository;

import com.InkSpace.app.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Integer> {
	List<Emprestimo> findByMembroIdAndDataDevolucaoIsNull(Integer membroId);
	boolean existsByLivroIdAndMembroIdAndDataDevolucaoIsNull(Integer livroId, Integer membroId);
}
