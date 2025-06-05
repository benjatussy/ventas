package com.api_ventas.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api_ventas.dto.VentaDTO;
import com.api_ventas.model.Venta;
import com.api_ventas.repository.VentaRepository;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    private VentaDTO toDTO(Venta venta){
        return new VentaDTO(
            venta.getIdVenta(),
            venta.getIdCliente(),
            venta.getIdVendedor(),
            venta.getFecha_venta()
        );
    }

    private Venta toEntity(VentaDTO dto) {
        Venta venta = new Venta();
        venta.setIdVenta(dto.getId());
        venta.setIdCliente(dto.getIdCliente());
        venta.setIdVendedor(dto.getIdVendedor());
        venta.setFecha_venta(dto.getFechaVenta());
        return venta;
    }

    public VentaDTO crear(VentaDTO dto) {
        Venta venta = toEntity(dto);
        return toDTO(ventaRepository.save(venta));
    }

    public List<VentaDTO> listar() {
        return ventaRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public VentaDTO obtenerPorId(Integer id) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
        return toDTO(venta);
    }

    public VentaDTO actualizar(Integer id, VentaDTO dto) {
        Venta existente = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        existente.setIdCliente(dto.getIdCliente());
        existente.setIdVendedor(dto.getIdVendedor());
        existente.setFecha_venta(dto.getFechaVenta());

        return toDTO(ventaRepository.save(existente));
    }

    public void eliminar(Integer id) {
        ventaRepository.deleteById(id);
    }
}