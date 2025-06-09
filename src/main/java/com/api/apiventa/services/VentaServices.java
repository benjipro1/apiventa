package com.api.apiventa.services;

import com.api.apiventa.dto.VentaDto;
import com.api.apiventa.models.Venta;
import com.api.apiventa.repository.VentaRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

@Service
public class VentaServices {
    @Autowired
    private VentaRepository ventaRepository;
    private VentaDto toDto(Venta venta) {
        return new VentaDto(
            venta.getIdVenta(),
            venta.getFecha(),
            venta.getTotal()
        );
    }

    private Venta toEntity(VentaDto dto) {
        Venta venta = new Venta();
        venta.setIdVenta(dto.getIdVenta());
        venta.setFecha(dto.getFecha());
        venta.setTotal(dto.getTotal());
        return venta;
    }

    public List<VentaDto> getAllVentas() {
        return ventaRepository.findAll().stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    }

    public VentaDto crear(VentaDto dto) {
        Venta venta = toEntity(dto);
        return toDto(ventaRepository.save(venta));
    }

    public VentaDto buscar(Integer id) {
        Venta venta = ventaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
        return toDto(venta);
    }

    public VentaDto actualizar(Integer id, VentaDto dto) {
        Venta venta = ventaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
        venta.setFecha(dto.getFecha());
        venta.setTotal(dto.getTotal());
        return toDto(ventaRepository.save(venta));
    }

    public void eliminar(Integer id) {
        ventaRepository.deleteById(id);
    }
    
}
