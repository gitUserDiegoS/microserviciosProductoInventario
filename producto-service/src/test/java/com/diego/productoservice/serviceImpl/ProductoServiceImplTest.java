package com.diego.productoservice.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

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
import com.diego.productoservice.repository.ProductoRepository;
import com.diego.productoservice.request.ProductoRequest;
import com.diego.productoservice.response.ProductoResponse;
import com.diego.productoservice.service.impl.ProductoServiceImpl;

@ExtendWith(MockitoExtension.class)
class ProductoServiceImplTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoServiceImpl productoService;

    private Producto producto;

    @BeforeEach
    void setUp() {
        producto = Producto.builder()
                .id(1L)
                .nombre("nombreProducto")
                .precio(1000.0)
                .build();
    }

    @Test
    void testCrearProducto() {
        ProductoRequest request = ProductoRequest.builder()
                .nombre("nombreProducto")
                .precio(1000.0)
                .build();

        when(productoRepository.save(any(Producto.class))).thenReturn(producto);

        ResponseEntity<ProductoResponse> response = productoService.crearProducto(request);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("nombreProducto", response.getBody().getNombre());
        verify(productoRepository).save(any(Producto.class));
    }

    @Test
    void testListarProductos() {
        PageRequest pageable = PageRequest.of(0, 10);
        Page<Producto> page = new PageImpl<>(List.of(producto));

        when(productoRepository.findAll(pageable)).thenReturn(page);

        Page<Producto> result = productoService.listarProductos(pageable);

        assertEquals(1, result.getContent().size());
        assertEquals("nombreProducto", result.getContent().get(0).getNombre());
    }

    @Test
    void testListarPorIdExistente() {
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        ResponseEntity<ProductoResponse> response = productoService.listarPorId(1L);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("nombreProducto", response.getBody().getNombre());
    }

}
