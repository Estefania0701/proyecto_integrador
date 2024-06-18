package backendc3.ClinicaOdontologica.controller;

import backendc3.ClinicaOdontologica.entity.Odontologo;
import backendc3.ClinicaOdontologica.entity.Paciente;
import backendc3.ClinicaOdontologica.entity.Turno;
import backendc3.ClinicaOdontologica.exception.ResourceNotFoundException;
import backendc3.ClinicaOdontologica.service.OdontologoService;
import backendc3.ClinicaOdontologica.service.PacienteService;
import backendc3.ClinicaOdontologica.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    @Autowired
    private TurnoService turnoService;




    @GetMapping
    public ResponseEntity<List<Turno>> buscarTodos() {
        return ResponseEntity.ok(this.turnoService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turno> buscarPorId(@PathVariable Long id) {
        Turno turnoBuscado = turnoService.buscarPorId(id);
        if (turnoBuscado != null) {
            return ResponseEntity.ok(turnoBuscado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Turno> guardarTurno(
            @RequestBody Turno turno) throws ResourceNotFoundException {
        Turno turnoGuardado = turnoService.guardar(turno);
        if (turnoGuardado != null) {
            return ResponseEntity.ok(turnoGuardado);
        }
        throw new ResourceNotFoundException("Paciente u odontólogo no encontrado");
    }

    @PutMapping
    public ResponseEntity<Turno> actualizarTurno(
            @RequestBody Turno turno) {
        Turno turnoActualizado = turnoService.actualizar(turno);
        if (turnoActualizado != null) {
            return ResponseEntity.ok(turnoActualizado);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) throws ResourceNotFoundException {
        boolean eliminado = turnoService.eliminar(id);
        if (eliminado) {
            return ResponseEntity.ok("Turno eliminado");
        }
        throw new ResourceNotFoundException("Turno no encontrado");
    }

}
