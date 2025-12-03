package ar.org.centro8.java.curso.services;

import org.springframework.stereotype.Service;

import ar.org.centro8.java.curso.model.entity.Cliente;
import ar.org.centro8.java.curso.model.repository.ClienteRepository;

import java.util.List;

@Service
public class ClienteService {
    private final ClienteRepository repo;

    public ClienteService(ClienteRepository repo) {
        this.repo = repo;
    }

    public List<Cliente> listar() {
        return repo.findAll();
    }

    public Cliente buscar(int id) {
        return repo.findById(id);
    }

    public void guardar(Cliente c) {
        repo.save(c);
    }

    public void actualizar(Cliente c) {
        repo.update(c);
    }

    public void eliminar(int id) {
        repo.delete(id);
    }
}
