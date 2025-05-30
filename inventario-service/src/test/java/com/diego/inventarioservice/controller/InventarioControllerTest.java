package com.diego.inventarioservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.diego.inventarioservice.dto.ProductoDto;
import com.diego.inventarioservice.response.ConsultaResponse;
import com.diego.inventarioservice.service.InventarioService;

@ExtendWith(MockitoExtension.class)
public class InventarioControllerTest {

    @Mock
    private InventarioService inventarioService;

    @InjectMocks
    private InventarioController inventarioController;

    private ConsultaResponse consultaResponse;

    @BeforeEach
    void setUp() {

        consultaResponse = ConsultaResponse.builder()
                .productoDto(ProductoDto.builder().id(1L)
                        .nombre("productoNombre")
                        .precio(50000d)
                        .build())
                .cantidad(20).build();

    }

    @Test
    void testCconsultarCantidadProducto() {

        ResponseEntity<ConsultaResponse> inventario = ResponseEntity.ok(consultaResponse);

        when(inventarioService.consultarPorId(any(Long.class))).thenReturn(inventario);

        ResponseEntity<ConsultaResponse> response = inventarioController.consultarInventario(1L);
        assertEquals(200, response.getStatusCode().value());
    }
}
