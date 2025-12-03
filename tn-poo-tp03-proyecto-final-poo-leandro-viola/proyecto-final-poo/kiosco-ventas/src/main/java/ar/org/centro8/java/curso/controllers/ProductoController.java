package ar.org.centro8.java.curso.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.org.centro8.java.curso.model.entity.Producto;
import ar.org.centro8.java.curso.services.ProductoService;

@Controller
@RequestMapping("/productos")
public class ProductoController {
    private final ProductoService service;
    
    public ProductoController(ProductoService service){ 
        this.service = service; 
    }

    @GetMapping
    public String list(Model model){
        model.addAttribute("productos", service.listar());
        return "productos-list";
    }

    @GetMapping("/alta")
    public String alta(Model model){
        model.addAttribute("producto", new Producto());
        return "producto-alta";
    }

    @PostMapping("/guardar")
    public String guardar(Producto p){
        service.guardar(p);
        return "redirect:/productos";
    }

    @GetMapping("/editar")
    public String editar(@RequestParam int id, Model model){
        model.addAttribute("producto", service.buscar(id));
        return "producto-editar";
    }

    @PostMapping("/actualizar")
    public String actualizar(Producto p){
        service.actualizar(p);
        return "redirect:/productos";
    }

    @GetMapping("/eliminar")
    public String eliminar(@RequestParam int id, RedirectAttributes ra) {
        //RedirectAttributes: Es una forma de pasar datos (mensajes) a la siguiente petición después de un redirect.
        //Se utiliza para mandar mensajes cuando se elimina o no se puede eliminar algo.
        // Es una utilidad de Spring MVC para mostrar mensajes después de una redirección.
        try {
            service.eliminar(id);
            ra.addFlashAttribute("mensaje", "Producto eliminado correctamente.");
            //addFlashAttribute es un método de la clase RedirectAttributes que sirve para enviar datos desde un controlador hacia la vista, pero a través de una redirección.
        } catch (DataIntegrityViolationException e) {
            //Si falla algo con la base de datos, Spring lo envuelve en una excepción de tipo DataAccessException y una de sus hijas es DataIntegrityViolationException
            //Cuando intentamos borrar un cliente con ventas asociadas, la base de datos se queja por la clave foránea. 
            // Spring lanza esta DataIntegrityViolationException y la usamos para mostrar un mensaje amigable en pantalla.
            ra.addFlashAttribute("error",
                "No se puede eliminar el producto porque tiene ventas asociadas. " +
                "Para eliminarlo, primero debe eliminar o anular esas ventas."
            );
        }
        return "redirect:/productos"; //Hace que el navegador haga una nueva petición GET a /productos.
        //Es una forma típica de evitar recargar el formulario y volver a mandar el POST
    }

}
