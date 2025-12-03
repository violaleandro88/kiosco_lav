package ar.org.centro8.java.curso.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.org.centro8.java.curso.model.entity.Cliente;
import ar.org.centro8.java.curso.model.entity.Producto;
import ar.org.centro8.java.curso.model.entity.Venta;
import ar.org.centro8.java.curso.services.ClienteService;
import ar.org.centro8.java.curso.services.ProductoService;
import ar.org.centro8.java.curso.services.VentaService;

import java.util.*;

@Controller
@RequestMapping("/ventas")
public class VentaController {
    private final VentaService ventaService;
    private final ProductoService productoService;
    private final ClienteService clienteService;

    public VentaController(VentaService ventaService, ProductoService productoService, ClienteService clienteService) {
        this.ventaService = ventaService;
        this.productoService = productoService;
        this.clienteService = clienteService;
    }

    @GetMapping
    public String listar(Model model) {
        List<Venta> ventas = ventaService.listar();
        List<Cliente> clientes = clienteService.listar();

        Map<Integer, String> clientesDisplay = new HashMap<>();

        for (Cliente c : clientes) {
            String texto = c.getApellido() + " " + c.getNombre() + " - DNI " + c.getDni();
            clientesDisplay.put(c.getId(), texto);
        }

        model.addAttribute("ventas", ventas);
        model.addAttribute("clientesDisplay", clientesDisplay);

        return "ventas-list";
    }

    @GetMapping("/alta")
    public String alta(Model model){
        model.addAttribute("productos", productoService.listar());
        model.addAttribute("clientes", clienteService.listar());
        return "venta-alta";
    }

    @PostMapping("/guardar")
    public String guardar(@RequestParam int idCliente,
                          @RequestParam(name = "productoId", required = false) List<Integer> productoIds,
                          @RequestParam(name = "cantidad", required = false) List<Integer> cantidades){
        List<Map<String,Integer>> items = new ArrayList<>();
        if (productoIds != null && cantidades != null) {
            for (int i = 0; i < productoIds.size(); i++) {
                Integer pid = productoIds.get(i);
                Integer qty = cantidades.get(i);
                if (pid != null && qty != null && qty > 0) {
                    Map<String,Integer> map = new HashMap<>();
                    map.put("idProducto", pid);
                    map.put("cantidad", qty);
                    items.add(map);
                }
            }
        }
        ventaService.crearVenta(idCliente, items);
        return "redirect:/ventas";
    }

    @GetMapping("/detalle")
    public String detalle(@RequestParam int id, Model model){
        Venta v = ventaService.buscar(id);

        Map<Integer,String> clientesDisplay = new HashMap<>();
        for (Cliente c : clienteService.listar()) {
            String texto = c.getApellido() + " " + c.getNombre() + " - DNI " + c.getDni();
            clientesDisplay.put(c.getId(), texto);
        }

        Map<Integer,String> productosDisplay = new HashMap<>();
        for (Producto p : productoService.listar()) {
            productosDisplay.put(p.getId(), p.getNombre());
        }

        model.addAttribute("venta", v);
        model.addAttribute("detalles", ventaService.detalles(id)); 
        model.addAttribute("clientesDisplay", clientesDisplay);
        model.addAttribute("productosDisplay", productosDisplay);

        return "ventas-detalle";
    }


    @GetMapping("/eliminar")
    public String eliminar(@RequestParam int id, RedirectAttributes ra) {
        try {
            ventaService.eliminar(id);
            ra.addFlashAttribute("mensaje", "Venta eliminada correctamente.");
        } catch (DataIntegrityViolationException e) {
            ra.addFlashAttribute("error",
                "No se puede eliminar la venta porque tiene información asociada. " +
                "Verifique los detalles de venta antes de eliminar."
            );
        }
        return "redirect:/ventas";
    }


}
