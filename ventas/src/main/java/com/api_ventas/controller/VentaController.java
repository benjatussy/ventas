package com.api_ventas.controller;

import com.api_ventas.dto.VentaDTO;
import com.api_ventas.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @PostMapping
    public ResponseEntity<VentaDTO> crear(@RequestBody VentaDTO dto) {
        return ResponseEntity.ok(ventaService.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<VentaDTO>> listar() {
        return ResponseEntity.ok(ventaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaDTO> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(ventaService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VentaDTO> actualizar(@PathVariable Integer id, @RequestBody VentaDTO dto) {
        return ResponseEntity.ok(ventaService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        ventaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
