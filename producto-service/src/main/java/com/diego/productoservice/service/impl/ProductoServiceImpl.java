package com.diego.productoservice.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.diego.productoservice.entity.Producto;
import com.diego.productoservice.repository.ProductoRepository;
import com.diego.productoservice.request.ProductoRequest;
import com.diego.productoservice.response.ProductoResponse;
import com.diego.productoservice.service.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public ResponseEntity<ProductoResponse> crearProducto(ProductoRequest producto) {
        Producto guardarProducto = productoRepository.save(Producto.builder()
                .nombre(producto.getNombre())
                .precio(producto.getPrecio()).build());

        // debe ir logica aca de crear inventario inicail cuando producto nuevo

        return ResponseEntity.ok(ProductoResponse.builder()
                .id(guardarProducto.getId())
                .nombre(guardarProducto.getNombre())
                .precio(guardarProducto.getPrecio()).build());
    }

    @Override
    public Page<Producto> listarProductos(org.springframework.data.domain.Pageable paginacion) {

        return productoRepository.findAll(paginacion);
    }

    @Override
    public ResponseEntity<ProductoResponse> listarPorId(Long id) {

        System.out.println("Entra a consulta findbyid en producto service " + id + " desde el otro");

        ResponseEntity<Producto> consulta = productoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

        return ResponseEntity.ok(ProductoResponse.builder()
                .id(consulta.getBody().getId())
                .nombre(consulta.getBody().getNombre())
                .precio(consulta.getBody().getPrecio()).build());

    }

}
