package com.api.apiventa.services;

import com.api.apiventa.dto.DetalleVentaDto;
import com.api.apiventa.models.DetalleVenta;
import com.api.apiventa.repository.DetalleVentaRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;


@Service
public class DetalleVentaServices {
    @Autowired
    private DetalleVentaRepository detalleVentaRepository;
    private DetalleVentaDto toDto(DetalleVenta detalleVenta) {
        return new DetalleVentaDto(
            detalleVenta.getIdDetalleVenta()
        );
    }

    private DetalleVenta toEntity(DetalleVentaDto dto) {
        DetalleVenta detalleVenta = new DetalleVenta();
        detalleVenta.setIdDetalleVenta(dto.getIdDetalleVenta());
        return detalleVenta;
    }

    public List<DetalleVentaDto> getAllDetalles() {
        return detalleVentaRepository.findAll().stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    }

    public DetalleVentaDto crear(DetalleVentaDto dto) {
        DetalleVenta detalleVenta = toEntity(dto);
        return toDto(detalleVentaRepository.save(detalleVenta));
    }

    public DetalleVentaDto buscar(Integer id) {
        DetalleVenta detalleVenta = detalleVentaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Detalle de venta no encontrado"));
        return toDto(detalleVenta);
    }

    public DetalleVentaDto actualizar(Integer id, DetalleVentaDto dto) {
        DetalleVenta detalleVenta = detalleVentaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Detalle de venta no encontrado"));
        detalleVenta.setIdDetalleVenta(dto.getIdDetalleVenta());
        return toDto(detalleVentaRepository.save(detalleVenta));
    }

    public void eliminar(Integer id) {
        detalleVentaRepository.deleteById(id);
    }

}
