package ar.org.centro8.java.curso.model.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import ar.org.centro8.java.curso.model.entity.Producto;
import ar.org.centro8.java.curso.model.repository.interfaces.IProductoRepository;

import java.util.List;

@Repository
public class ProductoRepository implements IProductoRepository{

    private final JdbcTemplate jdbc;

    private final RowMapper<Producto> mapper = (rs, rowNum) -> {
        Producto p = new Producto();
        p.setId(rs.getInt("id"));
        p.setNombre(rs.getString("nombre"));
        p.setPrecioCompra(rs.getDouble("precio_compra"));
        p.setPrecioVenta(rs.getDouble("precio_venta"));
        p.setStock(rs.getInt("stock"));
        return p;
    };

    public ProductoRepository(JdbcTemplate jdbc) { this.jdbc = jdbc; }

    @Override
    public List<Producto> findAll() {
        return jdbc.query("select * from productos order by id", mapper);
    }

    @Override
    public Producto findById(int id) {
        return jdbc.queryForObject("select * from productos where id=?", mapper, id);
    }

    @Override
    public void save(Producto p) {
        jdbc.update("insert into productos (nombre, precio_compra, precio_venta, stock) values (?,?,?,?)",
                p.getNombre(), p.getPrecioCompra(), p.getPrecioVenta(), p.getStock());
    }

    @Override
    public void update(Producto p) {
        jdbc.update("update productos set nombre=?, precio_compra=?, precio_venta=?, stock=? where id=?",
                p.getNombre(), p.getPrecioCompra(), p.getPrecioVenta(), p.getStock(), p.getId());
    }

    @Override
    public void delete(int id) {
        jdbc.update("delete from productos where id=?", id);
    }
}
