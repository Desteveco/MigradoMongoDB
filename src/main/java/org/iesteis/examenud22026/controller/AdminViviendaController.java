package org.iesteis.examenud22026.controller;

import org.iesteis.examenud22026.model.Vivienda;
import org.iesteis.examenud22026.service.ViviendaService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/admin/viviendas")
@PreAuthorize("hasRole('ADMIN')")
public class AdminViviendaController {

    private final ViviendaService viviendaService;

    public AdminViviendaController(ViviendaService viviendaService) {
        this.viviendaService = viviendaService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("viviendas", viviendaService.findAll());
        return "admin/viviendas/list";
    }

    @GetMapping("/nuevo")
    public String nuevoForm(Model model) {
        model.addAttribute("vivienda", new Vivienda());
        return "admin/viviendas/form";
    }

    @PostMapping
    public String create(@ModelAttribute Vivienda vivienda) {
        if (vivienda.getFechaPublicacion() == null) {
            vivienda.setFechaPublicacion(LocalDate.now());
        }
        viviendaService.save(vivienda);
        return "redirect:/admin/viviendas";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable String id, Model model) {
        var opt = viviendaService.findById(id);
        if (opt.isEmpty()) {
            return "redirect:/admin/viviendas";
        }
        model.addAttribute("vivienda", opt.get());
        return "admin/viviendas/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable String id, @ModelAttribute Vivienda vivienda) {
        vivienda.setId(id);
        viviendaService.save(vivienda);
        return "redirect:/admin/viviendas";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        viviendaService.deleteById(id);
        return "redirect:/admin/viviendas";
    }
}
