package com.api_ventas.dto;

import lombok.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VentaDTO {

    private Integer id;
    private Integer idCliente;
    private Integer idVendedor;
    private Date fechaVenta;

}
