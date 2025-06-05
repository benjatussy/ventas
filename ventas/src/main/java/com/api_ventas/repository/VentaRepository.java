package com.api_ventas.repository;


import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.api_ventas.model.Venta;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {

}
