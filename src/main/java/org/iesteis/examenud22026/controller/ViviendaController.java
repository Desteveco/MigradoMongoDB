package org.iesteis.examenud22026.controller;

import org.iesteis.examenud22026.service.ViviendaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
public class ViviendaController {

    private final ViviendaService viviendaService;

    public ViviendaController(ViviendaService viviendaService) {
        this.viviendaService = viviendaService;
    }

    @GetMapping({"/", "/viviendas"})
    public String list(Model model,
                       @RequestParam(value = "query", required = false) String query,
                       @RequestParam(value = "queryMenor", required = false) String queryMenor,
                       @RequestParam(value = "queryMayor", required = false) String queryMayor) {
        if (query != null && !query.isEmpty() && queryMenor != null && queryMenor.isEmpty() && queryMayor != null && queryMayor.isEmpty()) {
            model.addAttribute("viviendas", viviendaService.findAll().stream()
                    .filter(v -> v.getTitulo() != null && v.getTitulo().toLowerCase().contains(query.toLowerCase()))
                    .toList());
        } else if  (query != null && !query.isEmpty() && queryMenor != null && !queryMenor.isEmpty() && queryMayor != null && queryMayor.isEmpty()) {
            model.addAttribute("viviendas", viviendaService.findByPriceMenorQue(queryMenor).stream()
                    .filter(v -> v.getTitulo() != null && v.getTitulo().toLowerCase().contains(query.toLowerCase()))
                    .toList());
        } else if   (query != null && !query.isEmpty() && queryMenor != null && queryMenor.isEmpty() && queryMayor != null && !queryMayor.isEmpty()) {
            model.addAttribute("viviendas", viviendaService.findByPriceMayorQue(queryMayor).stream()
                    .filter(v -> v.getTitulo() != null && v.getTitulo().toLowerCase().contains(query.toLowerCase()))
                    .toList());
        } else if (query != null && !query.isEmpty() && queryMenor != null && !queryMenor.isEmpty() && !queryMayor.isEmpty() && queryMayor != null ){
            model.addAttribute("viviendas", viviendaService
                    .findByPriceBetween(queryMayor, queryMenor)
                    .stream()
                    .filter(v -> v.getTitulo() != null && v.getTitulo().toLowerCase().contains(query.toLowerCase()))
                    .toList());
        } else if (query != null && query.isEmpty() && queryMenor != null && !queryMenor.isEmpty() && queryMayor != null && queryMayor.isEmpty()){
            model.addAttribute("viviendas", viviendaService.findByPriceMenorQue(queryMenor));
        } else if (query != null && query.isEmpty() && queryMenor != null && queryMenor.isEmpty() && queryMayor != null && !queryMayor.isEmpty()){
            model.addAttribute("viviendas",  viviendaService.findByPriceMayorQue(queryMayor));
        } else if (query != null && query.isEmpty() && queryMenor != null && !queryMenor.isEmpty() && !queryMayor.isEmpty() && queryMayor != null ){
            model.addAttribute("viviendas", viviendaService.findByPriceBetween(queryMayor, queryMenor));
        }
        else {
            model.addAttribute("viviendas", viviendaService.findAvailable());
        }
        model.addAttribute("query", query);
        model.addAttribute("queryMenor", queryMenor);
        model.addAttribute("queryMayor", queryMayor);
        return "viviendas/list";
    }

    @GetMapping("/viviendas/{id}")
    public String detail(Model model, @PathVariable String id) {
        var opt = viviendaService.findById(id);
        if (opt.isEmpty()) {
            return "redirect:/viviendas";
        }
        model.addAttribute("vivienda", opt.get());
        return "viviendas/detail";
    }
}
