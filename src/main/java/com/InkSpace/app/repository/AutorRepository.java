package com.InkSpace.app.repository;

import com.InkSpace.app.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Integer> {
	Optional<Autor> findByNomeAndSobrenome(String nome, String sobrenome);
}
