package ar.org.centro8.java.curso.model.repository.interfaces;

import java.time.LocalDateTime;
import java.util.List;

import ar.org.centro8.java.curso.model.entity.Venta;
import ar.org.centro8.java.curso.model.enums.EstadoVenta;

public interface IVentaRepository {
    List<Venta> findAll();
    Venta findById(int id);
    int save(int idCliente, LocalDateTime fecha, EstadoVenta estado, double total);
    void updateEstadoYTotal(int id, EstadoVenta estado, double total);
    void delete(int id);
}
