package com.InkSpace.app.repository;
import com.InkSpace.app.model.Multa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MultaRepository extends JpaRepository<Multa, Integer> {
	List<Multa> findByEmprestimo_Membro_Id(Integer membroId);
}