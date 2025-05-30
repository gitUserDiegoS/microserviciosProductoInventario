package com.diego.productoservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diego.productoservice.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
