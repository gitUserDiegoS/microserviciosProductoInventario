package com.diego.inventarioservice.serviceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.ResponseEntity;

import com.diego.inventarioservice.clientefeign.ProductoClient;
import com.diego.inventarioservice.dto.ProductoDto;
import com.diego.inventarioservice.entity.Inventario;
import com.diego.inventarioservice.repository.InventarioRepository;
import com.diego.inventarioservice.response.ConsultaResponse;
import com.diego.inventarioservice.service.impl.InventarioServiceImpl;

@ExtendWith(MockitoExtension.class)
class InventarioServiceImplTest {

    @Mock
    private InventarioRepository inventarioRepo;

    @Mock
    private ProductoClient productoClient;

    @InjectMocks
    private InventarioServiceImpl inventarioService;

    private Inventario inventario;
    private ProductoDto productoDto;

    @BeforeEach
    void setUp() {
        inventario = Inventario.builder()
                .productoId(1L)
                .cantidad(50)
                .build();

        productoDto = ProductoDto.builder()
                .id(1L)
                .nombre("Producto test")
                .precio(100.0)
                .build();
    }

    @Test
    void testConsultarPorIdNotFound() {
        when(inventarioRepo.findById(1L)).thenReturn(Optional.empty());

        var response = inventarioService.consultarPorId(1L);

        assertEquals(404, response.getStatusCodeValue());
        verify(inventarioRepo, times(1)).findById(1L);
        verifyNoInteractions(productoClient);
    }

    @Test
    void testConsultarPorIdSuccess() {
        when(inventarioRepo.findById(1L)).thenReturn(Optional.of(inventario));
        when(productoClient.obtenerProducto(eq(1L), anyString())).thenReturn(productoDto);

        ResponseEntity<ConsultaResponse> response = inventarioService.consultarPorId(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(50, response.getBody().getCantidad());
        assertEquals("Producto test", response.getBody().getProductoDto().getNombre());

        verify(inventarioRepo).findById(1L);
        verify(productoClient).obtenerProducto(eq(1L), anyString());
    }
}
