package ar.org.centro8.java.curso.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.org.centro8.java.curso.model.entity.DetalleVenta;
import ar.org.centro8.java.curso.model.entity.Producto;
import ar.org.centro8.java.curso.model.entity.Venta;
import ar.org.centro8.java.curso.model.enums.EstadoVenta;
import ar.org.centro8.java.curso.model.repository.DetalleVentaRepository;
import ar.org.centro8.java.curso.model.repository.ProductoRepository;
import ar.org.centro8.java.curso.model.repository.VentaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class VentaService {
    private final VentaRepository ventaRepo;
    private final DetalleVentaRepository detalleRepo;
    private final ProductoRepository productoRepo;

    public VentaService(VentaRepository ventaRepo, DetalleVentaRepository detalleRepo,
            ProductoRepository productoRepo) {
        this.ventaRepo = ventaRepo;
        this.detalleRepo = detalleRepo;
        this.productoRepo = productoRepo;
    }

    public List<Venta> listar() {
        return ventaRepo.findAll();
    }

    public Venta buscar(int id) {
        return ventaRepo.findById(id);
    }

    @Transactional
    public int crearVenta(int idCliente, List<Map<String, Integer>> items) {
        // items: lista de { "idProducto": X, "cantidad": Y }
        int idVenta = ventaRepo.save(idCliente, LocalDateTime.now(), EstadoVenta.PENDIENTE, 0.0);

        double total = 0.0;
        for (Map<String, Integer> it : items) {
            Integer idProducto = it.get("idProducto");
            Integer cantidad = it.get("cantidad");
            if (idProducto == null || cantidad == null || cantidad <= 0)
                continue;

            Producto p = productoRepo.findById(idProducto);
            double pu = p.getPrecioVenta();
            double sub = pu * cantidad;
            total += sub;

            // Insert detalle
            DetalleVenta d = new DetalleVenta(idVenta, idProducto, pu, cantidad, sub);
            detalleRepo.save(d);

            // Descontar stock
            p.setStock(p.getStock() - cantidad);
            productoRepo.update(p);
        }

        ventaRepo.updateEstadoYTotal(idVenta, EstadoVenta.PAGADA, total);
        return idVenta;
    }

    public List<DetalleVenta> detalles(int idVenta) {
        return detalleRepo.findByVenta(idVenta);
    }

    public void eliminar(int idVenta) {
        detalleRepo.deleteByVenta(idVenta);
        ventaRepo.delete(idVenta);
    }
}
