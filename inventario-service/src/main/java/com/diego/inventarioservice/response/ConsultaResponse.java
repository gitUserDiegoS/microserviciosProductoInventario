package com.diego.inventarioservice.response;

import com.diego.inventarioservice.dto.ProductoDto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConsultaResponse {

    private ProductoDto productoDto;
    private double cantidad;

}
