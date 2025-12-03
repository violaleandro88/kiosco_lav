package ar.org.centro8.java.curso.model.entity;

import java.time.LocalDateTime;

import ar.org.centro8.java.curso.model.enums.EstadoVenta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor 
@AllArgsConstructor
public class Venta {
    private Integer id;
    private Integer idCliente;
    private LocalDateTime fecha;
    private EstadoVenta estado;
    private Double total;
}
