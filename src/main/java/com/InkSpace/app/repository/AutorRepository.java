package com.InkSpace.app.repository;

import com.InkSpace.app.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Integer> {

	@Query("SELECT m FROM Autor m WHERE m.id = :id")
	Autor findPorId(@Param("id")Integer id);

}
