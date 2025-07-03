package com.api.apiventa.controller;

import com.api.apiventa.services.VentaServices;
import com.api.apiventa.dto.VentaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo; 
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {
    @Autowired
    private VentaServices ventaServices;

    @GetMapping
    public ResponseEntity<List<VentaDto>> getAllVentas() {
        List<VentaDto> ventas = ventaServices.getAllVentas();
        return ResponseEntity.ok(ventas);
    }

    @PostMapping
    public ResponseEntity<VentaDto> crearVenta(@RequestBody VentaDto ventaDto) {
        VentaDto nuevaVenta = ventaServices.crear(ventaDto);
        return ResponseEntity.status(201).body(nuevaVenta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaDto> buscarVenta(@PathVariable Integer id) {
        VentaDto venta = ventaServices.buscar(id);
        return ResponseEntity.ok(venta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VentaDto> actualizarVenta(@PathVariable Integer id, @RequestBody VentaDto ventaDto) {
        VentaDto ventaActualizada = ventaServices.actualizar(id, ventaDto);
        return ResponseEntity.ok(ventaActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVenta(@PathVariable Integer id) {
        ventaServices.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/hateoas/{id}")
    public VentaDto obtenerHateoas(@PathVariable Integer id) {
        VentaDto dto = ventaServices.buscar(id);
        dto.add(linkTo(methodOn(VentaController.class).obtenerHateoas(id)).withSelfRel());
        dto.add(linkTo(methodOn(VentaController.class).listarHateoas()).withRel("TODOS"));
        dto.add(linkTo(methodOn(VentaController.class).eliminarVenta(id)).withRel("ELIMINAR"));
        return dto;
    }
    
    @GetMapping("/hateoas")
    public List<VentaDto> listarHateoas() {
        List<VentaDto> venta = ventaServices.getAllVentas();
        for (VentaDto dto : venta) {
            dto.add(linkTo(methodOn(VentaController.class).obtenerHateoas(dto.getIdVenta())).withSelfRel());
        }
        return venta;
    }
}
