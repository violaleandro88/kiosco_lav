package ar.org.centro8.java.curso.model.repository.interfaces;

import java.util.List;

import ar.org.centro8.java.curso.model.entity.Cliente;

public interface IClienteRepository {
    List<Cliente> findAll();
    Cliente findById(int id);
    void save(Cliente c);
    void update(Cliente c);
    void delete(int id);
}
