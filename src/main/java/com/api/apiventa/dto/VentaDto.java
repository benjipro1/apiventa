package com.api.apiventa.dto;
import lombok.*;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class VentaDto {
    private Integer IdVenta;
    private Date fecha;
    private int total;
}
