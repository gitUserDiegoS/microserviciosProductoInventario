package com.diego.inventarioservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diego.inventarioservice.response.ConsultaResponse;
import com.diego.inventarioservice.service.InventarioService;

/**
 * REST controller para recurso inventarios
 * 
 * @author Diego Sanchez
 */
@RestController
@RequestMapping("/api/inventarios")
public class InventarioController {

    /**
     * Servicio para gestionar pacientes.
     * 
     * @see InventarioService.class
     */
    private final InventarioService inventarioService;

    /**
     * Constructor para InventarioController.
     *
     * @param InventarioService servicio para gestionar recurso inventario
     */
    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }


    /**
     * Consulta de inventario con informacion del producto
     *
     * @param productoId con el id del producto
     * @return ResponseEntity con estado http 200
     */
    @GetMapping("/{productoId}")
    public ResponseEntity<ConsultaResponse> consultarInventario(@PathVariable Long productoId) {

        return inventarioService.consultarPorId(productoId);
    }
}