package com.api_ventas.controller;

import com.api_ventas.dto.VentaDTO;
import com.api_ventas.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo; 
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
    public ResponseEntity<VentaDTO> actualizar(@PathVariable Integer id, @RequestBody VentaDTO dto){
        return ResponseEntity.ok(ventaService.actualizar(id, dto));
    }

   
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        ventaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

      @GetMapping("/hateoas/{id}")
    public VentaDTO obtenerHATEOAS(@PathVariable Integer id) {
        VentaDTO dto = ventaService.obtenerPorId(id);
        
        dto.add(linkTo(methodOn(VentaController.class).obtenerHATEOAS(id)).withSelfRel());
        dto.add(linkTo(methodOn(VentaController.class).obtenerTodosHATEOAS()).withRel("todos"));
        dto.add(linkTo(methodOn(VentaController.class).eliminar(id)).withRel("eliminar"));

        return dto;
    }

    //METODO HATEOAS para listar todos los productos utilizando HATEOAS
    @GetMapping("/hateoas")
    public List<VentaDTO> obtenerTodosHATEOAS() {
        List<VentaDTO> lista = ventaService.listar();

        for (VentaDTO dto : lista) {
            dto.add(linkTo(methodOn(VentaController.class).obtenerHATEOAS(dto.getId())).withSelfRel());
        }

        return lista;
    }
}
