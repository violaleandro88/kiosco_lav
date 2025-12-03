package ar.org.centro8.java.curso.model.repository.interfaces;

import java.util.List;

import ar.org.centro8.java.curso.model.entity.Producto;

public interface IProductoRepository {
    List<Producto> findAll();
    Producto findById(int id);
    void save(Producto p);
    void update(Producto p);
    void delete(int id);
}
