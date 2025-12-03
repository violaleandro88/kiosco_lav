package ar.org.centro8.java.curso.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor 
@AllArgsConstructor
public class DetalleVenta {
    private Integer idVenta;
    private Integer idProducto;
    private Double precioUnitario;
    private Integer cantidad;
    private Double subtotal;
}
