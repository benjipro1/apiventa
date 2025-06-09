package com.api.apiventa.controller;

import com.api.apiventa.dto.DetalleVentaDto;
import com.api.apiventa.services.DetalleVentaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/detalles-venta")
public class DetalleVentaController {
    @Autowired
    private DetalleVentaServices detalleVentaServices;

    @GetMapping
    public ResponseEntity<List<DetalleVentaDto>> getAllDetalles() {
        List<DetalleVentaDto> detalles = detalleVentaServices.getAllDetalles();
        return ResponseEntity.ok(detalles);
    }

    @PostMapping
    public ResponseEntity<DetalleVentaDto> crearDetalle(@RequestBody DetalleVentaDto detalleVentaDto) {
        DetalleVentaDto nuevoDetalle = detalleVentaServices.crear(detalleVentaDto);
        return ResponseEntity.status(201).body(nuevoDetalle);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleVentaDto> buscarDetalle(@PathVariable Integer id) {
        DetalleVentaDto detalle = detalleVentaServices.buscar(id);
        return ResponseEntity.ok(detalle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleVentaDto> actualizarDetalle(@PathVariable Integer id, @RequestBody DetalleVentaDto detalleVentaDto) {
        DetalleVentaDto detalleActualizado = detalleVentaServices.actualizar(id, detalleVentaDto);
        return ResponseEntity.ok(detalleActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDetalle(@PathVariable Integer id) {
        detalleVentaServices.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
