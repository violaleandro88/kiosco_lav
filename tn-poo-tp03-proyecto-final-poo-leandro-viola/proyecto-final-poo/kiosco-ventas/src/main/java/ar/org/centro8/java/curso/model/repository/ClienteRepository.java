package ar.org.centro8.java.curso.model.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import ar.org.centro8.java.curso.model.entity.Cliente;
import ar.org.centro8.java.curso.model.repository.interfaces.IClienteRepository;

import java.util.List;

@Repository
public class ClienteRepository implements IClienteRepository {

    private final JdbcTemplate jdbc;

    private final RowMapper<Cliente> mapper = (rs, rowNum) -> {
        Cliente c = new Cliente();
        c.setId(rs.getInt("id"));
        c.setNombre(rs.getString("nombre"));
        c.setApellido(rs.getString("apellido"));
        c.setDni(rs.getString("dni"));
        c.setEmail(rs.getString("email"));
        c.setTelefono(rs.getString("telefono"));
        c.setActivo(rs.getBoolean("activo"));
        return c;
    };

    public ClienteRepository(JdbcTemplate jdbc) { 
        this.jdbc = jdbc; 
    }

    @Override
    public List<Cliente> findAll() {
        return jdbc.query("SELECT * FROM clientes ORDER BY nombre, apellido", mapper);
        //cuando utilizamos el método query, Spring pide la conexión, crea el PreparedStatement,
        //ejecuta la query, recorre cada fila, llama a mapper.mapRow(rs, rowNum) y finalmente cierra todo
    }

    @Override
    public Cliente findById(int id) {
        return jdbc.queryForObject("SELECT * FROM clientes WHERE id=?", mapper, id);
    }

    @Override
    public void save(Cliente c) {
        jdbc.update("INSERT INTO clientes (nombre, apellido, dni, email, telefono, activo) VALUES (?,?,?,?,?,?)",
                c.getNombre(), c.getApellido(), c.getDni(), c.getEmail(), c.getTelefono(), c.getActivo()==null?true:c.getActivo());
    }

    @Override
    public void update(Cliente c) {
        jdbc.update("UPDATE clientes SET nombre=?, apellido=?, dni=?, email=?, telefono=?, activo=? WHERE id=?",
                c.getNombre(), c.getApellido(), c.getDni(), c.getEmail(), c.getTelefono(), c.getActivo(), c.getId());
    }

    @Override
    public void delete(int id) {
        jdbc.update("DELETE FROM clientes WHERE id=?", id);
    }
}
