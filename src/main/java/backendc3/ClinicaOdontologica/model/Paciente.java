package backendc3.ClinicaOdontologica.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Paciente {

    private Integer id;

    private String nombre;

    private String apellido;

    private String cedula;

    private LocalDate fechaIngreso;

    private Domicilio domicilio;

    private String email;

    public Paciente(String nombre, String apellido, String cedula, LocalDate fechaIngreso, Domicilio domicilio, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
        this.email = email;
    }

    // Sobrecarga del constructor (doble constructor) para adaptarlo dependiendo de la necesidad
    public Paciente(Integer id, String nombre, String apellido, String cedula, LocalDate fechaIngreso, Domicilio domicilio, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
        this.email = email;
    }

    public Paciente() {
    }

}
