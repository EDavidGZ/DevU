package com.devU.devU.repositories;

import com.devU.devU.entities.HistorialCredito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHistorialCredito extends JpaRepository<HistorialCredito, Long> {
}
