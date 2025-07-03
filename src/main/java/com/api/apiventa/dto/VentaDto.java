package com.api.apiventa.dto;

import lombok.*;
import java.sql.Date;
import org.springframework.hateoas.RepresentationModel;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class VentaDto extends RepresentationModel<VentaDto> {
    private Integer IdVenta;
    private Date fecha;
    private int total;
}
