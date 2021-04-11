package com.orange.demo.web;

import com.orange.demo.domain.Persona;
import com.orange.demo.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class InicioController {

    @Autowired
    private PersonaService personaService;

    @GetMapping("/")
    public String init(Model model, @AuthenticationPrincipal User user) {
        Iterable<Persona> persona = personaService.listarPersonas();
        model.addAttribute("personas", persona);
        double saldoTotal = 0;
        int totalClientes = 0;

        for(Persona p: persona) {
            saldoTotal += p.getSaldo();
            totalClientes++;
        }

        model.addAttribute("saldoTotal", saldoTotal);
        model.addAttribute("totalClientes", totalClientes);
        return "index";
    }

    @GetMapping("/agregar")
    public String agregar(Persona persona) {
        return "modificar";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Persona persona, Errors errores) {
        if(errores.hasErrors()) {
            return "modificar";
        }
        personaService.guardar(persona);
        return "redirect:/";
    }

    @GetMapping("/editar/{idPersona}")
    public String editar(Persona persona, Model model) {
        Optional<Persona> persona1 = personaService.buscarPersona(persona);
        model.addAttribute("persona", persona1);
        return "modificar";
    }

    @GetMapping("/eliminar/{idPersona}")
    public String eliminar(Persona persona) {
        personaService.eliminar(persona);
        return "redirect:/";
    }
}
