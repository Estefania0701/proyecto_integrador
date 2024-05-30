package backendc3.ClinicaOdontologica.controller;

import backendc3.ClinicaOdontologica.model.Odontologo;
import backendc3.ClinicaOdontologica.model.Paciente;
import backendc3.ClinicaOdontologica.model.Turno;
import backendc3.ClinicaOdontologica.service.OdontologoService;
import backendc3.ClinicaOdontologica.service.PacienteService;
import backendc3.ClinicaOdontologica.service.TurnoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    private final TurnoService turnoService;

    private final PacienteService pacienteService;

    private final OdontologoService odontologoService;

    public TurnoController() {
        this.turnoService = new TurnoService();
        this.pacienteService = new PacienteService();
        this.odontologoService = new OdontologoService();
    }

    @GetMapping("")
    public ResponseEntity<List<Turno>> buscarTodos() {
        return ResponseEntity.ok(this.turnoService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turno> buscarPorId(@PathVariable Integer id) {
        Turno turnoBuscado = this.turnoService.buscarTurnoPorId(id);
        if (turnoBuscado != null) {
            return ResponseEntity.ok(turnoBuscado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<Turno> guardarTurno(
            @RequestBody Turno turno) {
        Paciente pacienteBuscado = this.pacienteService.buscarPorId(turno.getPaciente().getId());
        Odontologo odontologoBuscado = this.odontologoService.buscarPorId(turno.getOdontologo().getId());
        if (pacienteBuscado != null && odontologoBuscado != null) {
            turno.setPaciente(pacienteBuscado);
            turno.setOdontologo(odontologoBuscado);
            return ResponseEntity.ok(this.turnoService.guardarTurno(turno));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("")
    public ResponseEntity<Turno> actualizarTurno(
            @RequestBody Turno turno) {
        Turno turnoBuscado = this.turnoService.buscarTurnoPorId(turno.getId());
        if (turnoBuscado != null) {
            this.turnoService.actualizarTurno(turno);
            return ResponseEntity.ok(this.turnoService.buscarTurnoPorId(turno.getId()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
