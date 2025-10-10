package com.InkSpace.app.repository;
import com.InkSpace.app.model.StatusReserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatusReservaRepository extends JpaRepository<StatusReserva, Integer> {
	Optional<StatusReserva> findByDescricaoIgnoreCase(String valorStatus);
}