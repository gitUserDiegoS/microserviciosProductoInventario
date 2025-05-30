package com.diego.productoservice.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductoRequest {

    private String nombre;

    private Double precio;
}
