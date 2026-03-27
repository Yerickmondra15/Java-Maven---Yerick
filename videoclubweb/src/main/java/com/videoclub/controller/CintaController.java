package com.videoclub.controller;

import com.videoclub.entity.Cinta;
import com.videoclub.service.CintaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cintas")
public class CintaController {

    private final CintaService service;

    public CintaController(CintaService service) {
        this.service = service;
    }

    /**
     * Listar todas las cintas
     */
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("cintas", service.listar());
        return "cintas/lista";
    }

    /**
     * Ir a formulario de nueva cinta
     */
    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("cinta", new Cinta());
        return "cintas/formulario";
    }

    /**
     * Ir a formulario de edición
     */
    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Integer id, Model model) {
        var cinta = service.obtenerPorId(id);
        if (cinta.isPresent()) {
            model.addAttribute("cinta", cinta.get());
            return "cintas/formulario";
        }
        return "redirect:/cintas";
    }

    /**
     * Guardar o actualizar cinta
     */
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Cinta cinta) {
        service.guardar(cinta);
        return "redirect:/cintas";
    }

    /**
     * Eliminar cinta
     */
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return "redirect:/cintas";
    }

}
