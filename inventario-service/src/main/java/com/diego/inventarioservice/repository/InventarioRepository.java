package com.diego.inventarioservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diego.inventarioservice.entity.Inventario;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {
}
