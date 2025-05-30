package com.diego.productoservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.diego.productoservice.entity.Producto;
import com.diego.productoservice.request.ProductoRequest;
import com.diego.productoservice.response.ProductoResponse;

public interface ProductoService {

    ResponseEntity<ProductoResponse> crearProducto(ProductoRequest producto);

    Page<Producto> listarProductos(Pageable paginacion);

    ResponseEntity<ProductoResponse> listarPorId(Long id);

}
