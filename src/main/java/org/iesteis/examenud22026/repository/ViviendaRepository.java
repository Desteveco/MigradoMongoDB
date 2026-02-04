package org.iesteis.examenud22026.repository;

import org.iesteis.examenud22026.model.Vivienda;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ViviendaRepository extends MongoRepository<Vivienda, String> {
    List<Vivienda> findByDisponibleTrue();
    List<Vivienda> findByTituloContainingIgnoreCase(String titulo);
    List<Vivienda> findByPrecioIsLessThan(BigDecimal precioMenorQue);
    List<Vivienda> findByPrecioGreaterThan(BigDecimal precioMayorQue);
    List<Vivienda> findByPrecioBetween(BigDecimal precioMenorQue, BigDecimal precioMayorQue);
}
