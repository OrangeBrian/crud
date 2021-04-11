package com.orange.demo.service;

import com.orange.demo.domain.Persona;

import java.util.List;
import java.util.Optional;

public interface PersonaService {

    List<Persona> listarPersonas();

    void guardar(Persona persona);

    void eliminar(Persona persona);

    Optional<Persona> buscarPersona(Persona persona);
}
