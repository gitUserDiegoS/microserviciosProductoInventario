package com.diego.inventarioservice.service;

import org.springframework.http.ResponseEntity;

import com.diego.inventarioservice.response.ConsultaResponse;

public interface InventarioService {

    ResponseEntity<ConsultaResponse> consultarPorId(Long id);

}
