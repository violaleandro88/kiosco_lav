package ar.org.centro8.java.curso.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor 
@AllArgsConstructor
public class Cliente {
    private Integer id;
    private String nombre;
    private String apellido;
    private String dni;
    private String email;
    private String telefono;
    private Boolean activo;
}
