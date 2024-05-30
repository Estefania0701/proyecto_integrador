package backendc3.ClinicaOdontologica.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Turno {

    private Integer id;
    private LocalDate fecha;
    private Paciente paciente;
    private Odontologo odontologo;

    public Turno() {
    }

    public Turno(Integer id, LocalDate fecha, Paciente paciente, Odontologo odontologo) {
        this.id = id;
        this.fecha = fecha;
        this.paciente = paciente;
        this.odontologo = odontologo;
    }

    @Override
    public String toString() {
        return "Turno{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", paciente=" + paciente +
                ", odontologo=" + odontologo +
                '}';
    }
}
