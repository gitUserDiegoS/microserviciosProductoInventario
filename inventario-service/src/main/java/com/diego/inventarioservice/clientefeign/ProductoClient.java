package com.diego.inventarioservice.clientefeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.diego.inventarioservice.clientefeign.config.FeignConfig;
import com.diego.inventarioservice.dto.ProductoDto;

/**
 * Cliente feign para comunicarse con servicio producto-service
 * 
 * @author Diego Sanchez
 */
@FeignClient(name = "producto-service", url = "http://producto-service:8081", configuration = FeignConfig.class)
public interface ProductoClient {

    /**
     * Operacion Get para obtener la informacion del producto
     * 
     * @see CitaMedicaService.class
     * 
     * @param id     con el id del producto
     * @param apiKey con la palabra secreta para comunicar atraves de api key
     * 
     */
    @GetMapping("/api/productos/{id}")
    ProductoDto obtenerProducto(@PathVariable Long id, @RequestHeader("X-API-KEY") String apiKey);
}