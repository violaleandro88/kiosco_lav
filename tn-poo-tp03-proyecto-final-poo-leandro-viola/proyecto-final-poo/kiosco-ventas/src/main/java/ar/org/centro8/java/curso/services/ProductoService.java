package ar.org.centro8.java.curso.services;

import org.springframework.stereotype.Service;

import ar.org.centro8.java.curso.model.entity.Producto;
import ar.org.centro8.java.curso.model.repository.ProductoRepository;

import java.util.List;

@Service
public class ProductoService {
    private final ProductoRepository repo;

    public ProductoService(ProductoRepository repo) {
        this.repo = repo;
    }

    public List<Producto> listar() {
        return repo.findAll();
    }

    public Producto buscar(int id) {
        return repo.findById(id);
    }

    public void guardar(Producto p) {
        repo.save(p);
    }

    public void actualizar(Producto p) {
        repo.update(p);
    }

    public void eliminar(int id) {
       repo.delete(id);
    }
}
