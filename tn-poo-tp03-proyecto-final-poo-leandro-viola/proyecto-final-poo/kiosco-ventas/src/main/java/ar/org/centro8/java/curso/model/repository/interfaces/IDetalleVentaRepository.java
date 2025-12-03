package ar.org.centro8.java.curso.model.repository.interfaces;

import java.util.List;

import ar.org.centro8.java.curso.model.entity.DetalleVenta;

public interface IDetalleVentaRepository {
    List<DetalleVenta> findByVenta(int idVenta);
    void save(DetalleVenta d);
    void deleteByVenta(int idVenta);
}
