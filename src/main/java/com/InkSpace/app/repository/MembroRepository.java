package com.InkSpace.app.repository;

import com.InkSpace.app.model.Membro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MembroRepository extends JpaRepository<Membro, Integer> {

	@Query("SELECT m FROM Membro m WHERE m.id = :id")
	Membro findPorId(@Param("id")Integer id);

}
