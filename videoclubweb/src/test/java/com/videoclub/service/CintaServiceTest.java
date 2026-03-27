package com.videoclub.service;

import com.videoclub.entity.Cinta;
import com.videoclub.repository.CintaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CintaServiceTest {

    @Mock
    private CintaRepository repository;

    private CintaService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new CintaService(repository);
    }

    @Test
    void testListarCintas() {
        // Arrange
        Cinta cinta1 = new Cinta(1, "A", "1", "1", "Matrix", "Lana Wachowski", 1999, "Ciencia Ficción");
        Cinta cinta2 = new Cinta(2, "A", "1", "2", "Inception", "Christopher Nolan", 2010, "Thriller");
        List<Cinta> cintas = Arrays.asList(cinta1, cinta2);

        when(repository.findAll()).thenReturn(cintas);

        // Act
        List<Cinta> resultado = service.listar();

        // Assert
        assertEquals(2, resultado.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testObtenerPorId() {
        // Arrange
        Cinta cinta = new Cinta(1, "A", "1", "1", "Matrix", "Lana Wachowski", 1999, "Ciencia Ficción");
        when(repository.findById(1)).thenReturn(Optional.of(cinta));

        // Act
        Optional<Cinta> resultado = service.obtenerPorId(1);

        // Assert
        assertTrue(resultado.isPresent());
        assertEquals("Matrix", resultado.get().getTitulo());
    }

    @Test
    void testGuardarCinta() {
        // Arrange
        Cinta cinta = new Cinta(1, "A", "1", "1", "Matrix", "Lana Wachowski", 1999, "Ciencia Ficción");
        when(repository.save(cinta)).thenReturn(cinta);

        // Act
        Cinta resultado = service.guardar(cinta);

        // Assert
        assertNotNull(resultado);
        assertEquals("Matrix", resultado.getTitulo());
        verify(repository, times(1)).save(cinta);
    }

    @Test
    void testEliminarCinta() {
        // Arrange
        Integer id = 1;

        // Act
        service.eliminar(id);

        // Assert
        verify(repository, times(1)).deleteById(id);
    }
}
