package com.devU.devU.repositories;


import com.devU.devU.entities.SolicitudCredito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISolicitudCredito extends JpaRepository<SolicitudCredito, Long> {
}
