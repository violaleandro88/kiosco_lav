package ar.org.centro8.java.curso.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ar.org.centro8.java.curso.model.entity.Cliente;
import ar.org.centro8.java.curso.services.ClienteService;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
@RequestMapping("/clientes") //es un prefijo común para todos los métodos. En vez de repetir /clientes en cada método, lo ponemos una sola vez en la clase.
public class ClienteController {
    private final ClienteService service;

    public ClienteController(ClienteService service){ 
        this.service = service; 
    }

    @GetMapping
    public String list(Model model){
        model.addAttribute("clientes", service.listar());
        return "clientes-list";
    }

    @GetMapping("/alta")
    public String alta(Model model){
        model.addAttribute("cliente", new Cliente());
        return "cliente-alta";
    }

    @PostMapping("/guardar")
    public String guardar(Cliente c){
        if (c.getActivo() == null) c.setActivo(true);
        service.guardar(c);
        return "redirect:/clientes";
    }

    @GetMapping("/editar")
    public String editar(@RequestParam int id, Model model){
        model.addAttribute("cliente", service.buscar(id));
        return "cliente-editar";
    }

    @PostMapping("/actualizar")
    public String actualizar(Cliente c){
        service.actualizar(c);
        return "redirect:/clientes";
    }

    @GetMapping("/eliminar")
    public String eliminar(@RequestParam int id, RedirectAttributes ra) {
        //RedirectAttributes: Es una forma de pasar datos (mensajes) a la siguiente petición después de un redirect.
        //Se utiliza para mandar mensajes cuando se elimina o no se puede eliminar algo.
        // Es una utilidad de Spring MVC para mostrar mensajes después de una redirección.
        try {
            service.eliminar(id);
            ra.addFlashAttribute("mensaje", "Cliente eliminado correctamente.");
            //addFlashAttribute es un método de la clase RedirectAttributes que sirve para enviar datos desde un controlador hacia la vista, pero a través de una redirección.
        } catch (DataIntegrityViolationException e) {
            //Si falla algo con la base de datos, Spring lo envuelve en una excepción de tipo DataAccessException y una de sus hijas es DataIntegrityViolationException
            //Cuando intentamos borrar un cliente con ventas asociadas, la base de datos se queja por la clave foránea. 
            // Spring lanza esta DataIntegrityViolationException y la usamos para mostrar un mensaje amigable en pantalla.
            ra.addFlashAttribute("error",
                "No se puede eliminar el cliente porque tiene ventas asociadas. " +
                "Para eliminarlo, primero debe eliminar o anular esas ventas."
            );
        }
        return "redirect:/clientes"; //Hace que el navegador haga una nueva petición GET a /clientes.
        //Es una forma típica de evitar recargar el formulario y volver a mandar el POST
    }

}
