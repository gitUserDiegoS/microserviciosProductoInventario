package com.diego.inventarioservice.clientefeign.config;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Retryer;


/**
 * Configuracion de reintentos maximo de intentos 3
 * 
 * @author Diego Sanchez
 */
@Configuration
public class FeignConfig {

    /**
     * bean encargado de reintentos
     *
     *
     * @return new retryer
     */
    @Bean
    public Retryer retryer() {
        return new Retryer.Default(100, TimeUnit.SECONDS.toMillis(1), 3);
    }
}