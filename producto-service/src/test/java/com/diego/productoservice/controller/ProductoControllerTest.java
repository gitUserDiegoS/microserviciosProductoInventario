package com.diego.productoservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

import com.diego.productoservice.entity.Producto;
import com.diego.productoservice.request.ProductoRequest;
import com.diego.productoservice.response.ProductoResponse;
import com.diego.productoservice.service.ProductoService;

@ExtendWith(MockitoExtension.class)
public class ProductoControllerTest {

    @Mock
    private ProductoService productoService;

    @InjectMocks
    private ProductoController productoController;

    private Producto producto;

    private ResponseEntity<Producto> productoCreado;

    @BeforeEach
    void setUp() {

        producto = Producto.builder()
                .id(1l)
                .nombre("nombreProducto")
                .precio(10000d).build();

        productoCreado = ResponseEntity.ok(producto);

    }

    @Test
    void testCrearProducto() {

        ProductoRequest request = ProductoRequest.builder().nombre("nombreProducto").precio(10000d).build();

        ProductoResponse productoResponse = ProductoResponse.builder().nombre("nombreProducto").precio(10000d).build();

        ResponseEntity<ProductoResponse> productoResponseDos = ResponseEntity.ok(productoResponse);
        when(productoService.crearProducto(any(ProductoRequest.class))).thenReturn(productoResponseDos);

        ResponseEntity<ProductoResponse> response = productoController.crearProducto(request);
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    void testListarProductos() {
        Producto producto1 = Producto.builder()
                .id(1L)
                .nombre("ProductoA")
                .precio(10000d)
                .build();

        Producto producto2 = Producto.builder()
                .id(2L)
                .nombre("ProductoB")
                .precio(20000d)
                .build();

        PageRequest pageable = PageRequest.of(0, 10);
        Page<Producto> productoPage = new PageImpl<>(List.of(producto1, producto2));

        when(productoService.listarProductos(pageable)).thenReturn(productoPage);

        Page<Producto> response = productoController.listarProductos(pageable);

        assertEquals(2, response.getTotalElements());
        assertEquals("ProductoA", response.getContent().get(0).getNombre());
    }

    @Test
    void testObtenerPorId() {
        ProductoResponse productoResponse = ProductoResponse.builder().nombre("nombreProducto").precio(10000d).build();
        ResponseEntity<ProductoResponse> productoResponseDos = ResponseEntity.ok(productoResponse);

        when(productoService.listarPorId(1L)).thenReturn(ResponseEntity.ok(productoResponse));

        ResponseEntity<ProductoResponse> response = productoController.obtener(1L);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("nombreProducto", response.getBody().getNombre());
    }

}