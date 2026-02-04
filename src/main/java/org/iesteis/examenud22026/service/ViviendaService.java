package org.iesteis.examenud22026.service;

import org.iesteis.examenud22026.model.Vivienda;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ViviendaService {
    List<Vivienda> findAll();
    List<Vivienda> findAvailable();
    List<Vivienda> findByPriceMayorQue(String precioMayorQue);
    List<Vivienda> findByPriceMenorQue(String precioMenorQue);
    List <Vivienda> findByPriceBetween(String precioMenorQue, String precioMayorQue);
    Optional<Vivienda> findById(String id);
    Vivienda save(Vivienda vivienda);
    void deleteById(String id);
}
