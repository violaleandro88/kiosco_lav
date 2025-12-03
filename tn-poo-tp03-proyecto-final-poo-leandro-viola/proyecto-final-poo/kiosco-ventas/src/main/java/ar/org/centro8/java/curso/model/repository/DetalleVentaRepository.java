package ar.org.centro8.java.curso.model.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import ar.org.centro8.java.curso.model.entity.DetalleVenta;
import ar.org.centro8.java.curso.model.repository.interfaces.IDetalleVentaRepository;

import java.util.List;

@Repository
public class DetalleVentaRepository implements IDetalleVentaRepository{

    private final JdbcTemplate jdbc;

    private final RowMapper<DetalleVenta> mapper = (rs, rowNum) -> {
        DetalleVenta d = new DetalleVenta();
        d.setIdVenta(rs.getInt("id_venta"));
        d.setIdProducto(rs.getInt("id_producto"));
        d.setPrecioUnitario(rs.getDouble("precio_unitario"));
        d.setCantidad(rs.getInt("cantidad"));
        d.setSubtotal(rs.getDouble("subtotal"));
        return d;
    };

    public DetalleVentaRepository(JdbcTemplate jdbc) { this.jdbc = jdbc; }

    @Override
    public List<DetalleVenta> findByVenta(int idVenta) {
        return jdbc.query("SELECT * FROM detalle_ventas WHERE id_venta=?", mapper, idVenta);
    }

    @Override
    public void save(DetalleVenta d) {
        jdbc.update("INSERT INTO detalle_ventas (id_venta, id_producto, precio_unitario, cantidad, subtotal) VALUES (?,?,?,?,?)",
                d.getIdVenta(), d.getIdProducto(), d.getPrecioUnitario(), d.getCantidad(), d.getSubtotal());
    }

    @Override
    public void deleteByVenta(int idVenta) {
        jdbc.update("DELETE FROM detalle_ventas WHERE id_venta=?", idVenta);
    }
}
