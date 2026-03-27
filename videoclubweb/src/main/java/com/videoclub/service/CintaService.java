package com.videoclub.service;

import com.videoclub.entity.Cinta;
import com.videoclub.repository.CintaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CintaService {

    private final CintaRepository repository;

    public CintaService(CintaRepository repository) {
        this.repository = repository;
    }

    /**
     * Obtener todas las cintas
     */
    public List<Cinta> listar() {
        return repository.findAll();
    }

    /**
     * Obtener una cinta por ID
     */
    public Optional<Cinta> obtenerPorId(Integer id) {
        return repository.findById(id);
    }

    /**
     * Guardar una nueva cinta
     */
    public Cinta guardar(Cinta cinta) {
        return repository.save(cinta);
    }

    /**
     * Actualizar cinta existente
     */
    public Cinta actualizar(Integer id, Cinta cintaActualizada) {
        if (repository.existsById(id)) {
            cintaActualizada.setCodigoCinta(id);
            return repository.save(cintaActualizada);
        }
        return null;
    }

    /**
     * Eliminar una cinta
     */
    public void eliminar(Integer id) {
        repository.deleteById(id);
    }

}
