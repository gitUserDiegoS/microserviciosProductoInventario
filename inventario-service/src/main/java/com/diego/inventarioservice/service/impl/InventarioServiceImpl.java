package com.diego.inventarioservice.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.diego.inventarioservice.clientefeign.ProductoClient;
import com.diego.inventarioservice.dto.ProductoDto;
import com.diego.inventarioservice.repository.InventarioRepository;
import com.diego.inventarioservice.response.ConsultaResponse;
import com.diego.inventarioservice.service.InventarioService;

/**
 * Servicio InventarioServiceImpl que implementa los metodos de la interfaz
 *
 * @see InventarioService.class
 * @author Diego Sanchez
 */
@Service
public class InventarioServiceImpl implements InventarioService {

    private final InventarioRepository inventarioRepo;

    private final ProductoClient clienteFeign;

    private final String API_KEY = "123456";

    /**
     * Constructor para CitaMedicaServiceImpl.
     *
     * @param inventarioRepo Repositorio para gestionar inventarios @see
     *                       InventarioRepository.class
     * @param clienteFeign   Cliente feign para comunicarse con
     *                       producto-service @see
     *                       ProductoClient.class
     * 
     */
    public InventarioServiceImpl(InventarioRepository inventarioRepo, ProductoClient clienteFeign) {
        this.inventarioRepo = inventarioRepo;
        this.clienteFeign = clienteFeign;
    }

    /**
     * Consulta producto por Id, retorna infromacion del producto y cantridad en
     * inventario
     *
     *
     * @param id id del producto
     * @return ResponseEntity ConsultaResponse con informacion de la consulta
     */
    @Override
    public ResponseEntity<ConsultaResponse> consultarPorId(Long id) {
        var inventario = inventarioRepo.findById(id).orElse(null);
        if (inventario == null)
            return ResponseEntity.notFound().build();
        System.out.println("Producto encontrado" + inventario);
        System.out.println("Producto id" + inventario.getProductoId());
        var producto = clienteFeign.obtenerProducto(id, API_KEY);
        return ResponseEntity.ok(ConsultaResponse.builder()
                .productoDto(ProductoDto.builder().id(producto.getId())
                        .nombre(producto.getNombre())
                        .precio(producto.getPrecio())
                        .build())
                .cantidad(inventario.getCantidad()).build());

    }

}
