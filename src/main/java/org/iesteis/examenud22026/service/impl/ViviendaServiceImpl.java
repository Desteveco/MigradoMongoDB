package org.iesteis.examenud22026.service.impl;

import org.iesteis.examenud22026.model.Vivienda;
import org.iesteis.examenud22026.repository.ViviendaRepository;
import org.iesteis.examenud22026.service.ViviendaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ViviendaServiceImpl implements ViviendaService {

    private final ViviendaRepository viviendaRepository;

    public ViviendaServiceImpl(ViviendaRepository viviendaRepository) {
        this.viviendaRepository = viviendaRepository;
    }

    @Override
    public List<Vivienda> findAll() {
        return viviendaRepository.findAll();
    }

    @Override
    public List<Vivienda> findAvailable() {
        return viviendaRepository.findByDisponibleTrue();
    }

    @Override
    public List<Vivienda> findByPriceMenorQue(String menorQue) { return viviendaRepository.findByPrecioIsLessThan(BigDecimal.valueOf(Long.parseLong(menorQue)));}

    @Override
    public List<Vivienda> findByPriceMayorQue(String mayorQue) { return viviendaRepository.findByPrecioGreaterThan(BigDecimal.valueOf(Long.parseLong(mayorQue)));}

    @Override
    public List<Vivienda> findByPriceBetween(String precioMenorQue, String precioMayorQue) { return viviendaRepository.findByPrecioBetween(BigDecimal.valueOf(Long.parseLong(precioMenorQue)), BigDecimal.valueOf(Long.parseLong(precioMayorQue)));}

    @Override
    public Optional<Vivienda> findById(String id) {
        return viviendaRepository.findById(id);
    }

    @Override
    public Vivienda save(Vivienda vivienda) {
        // Basic validation
        if (vivienda.getPrecio() != null && vivienda.getPrecio().doubleValue() < 0) {
            throw new IllegalArgumentException("Precio no puede ser negativo");
        }
        return viviendaRepository.save(vivienda);
    }

    @Override
    public void deleteById(String id) {
        viviendaRepository.deleteById(id);
    }
}
