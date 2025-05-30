package com.diego.productoservice.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diego.productoservice.entity.Producto;
import com.diego.productoservice.request.ProductoRequest;
import com.diego.productoservice.response.ProductoResponse;
import com.diego.productoservice.service.ProductoService;

/**
 * REST controller para gestionar recurso producto *
 * 
 * @author Diego Sanchez
 */
@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    /**
     * Servicio para gestionar productos.
     * 
     * @see ProductoService.class
     */
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    /**
     * Endpoint para crear un producto.
     * 
     * @param producto request con el producto a crear
     * @return ResponseEntity con estado http 200 y la información del producto
     */
    @PostMapping
    public ResponseEntity<ProductoResponse> crearProducto(@RequestBody ProductoRequest producto) {

        return productoService.crearProducto(producto);

    }

    /**
     * Endpoint para Obtener listado de productos
     * 
     * @param pageable con informacion referente a paginacion
     * @return ResponseEntity listado de productos paginados
     */
    @GetMapping
    public Page<Producto> listarProductos(@PageableDefault(size = 10, sort = "id") Pageable pageable) {
        return productoService.listarProductos(pageable);

    }

    /**
     * Endpoint para Obtener producto por id
     * 
     * @param id con id del producto
     * @return ResponseEntity conproducto consultado
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponse> obtener(@PathVariable Long id) {
        return productoService.listarPorId(id);

    }
}
