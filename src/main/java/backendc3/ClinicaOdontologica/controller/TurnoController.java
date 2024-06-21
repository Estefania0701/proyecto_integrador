package backendc3.ClinicaOdontologica.controller;

import backendc3.ClinicaOdontologica.dto.TurnoDTO;
import backendc3.ClinicaOdontologica.exception.BadRequestException;
import backendc3.ClinicaOdontologica.exception.ResourceNotFoundException;
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
    public ResponseEntity<List<TurnoDTO>> buscarTodos() {
        return ResponseEntity.ok(this.turnoService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnoDTO> buscarPorId(@PathVariable Long id) throws ResourceNotFoundException {
        TurnoDTO turnoBuscado = turnoService.buscarPorId(id);
        if (turnoBuscado != null) {
            return ResponseEntity.ok(turnoBuscado);
        }
        throw new ResourceNotFoundException("Turno no encontrado");
    }

    @PostMapping
    public ResponseEntity<TurnoDTO> guardarTurno(
            @RequestBody TurnoDTO turnoDTO) throws ResourceNotFoundException {
        TurnoDTO turnoGuardado = turnoService.guardar(turnoDTO);
        if (turnoGuardado != null) {
            return ResponseEntity.ok(turnoGuardado);
        }
        throw new ResourceNotFoundException("Paciente u odontólogo no encontrado");
    }

    @PutMapping
    public ResponseEntity<TurnoDTO> actualizarTurno(
            @RequestBody TurnoDTO turno) throws BadRequestException {
        TurnoDTO turnoActualizado = turnoService.actualizar(turno);
        if (turnoActualizado != null) {
            return ResponseEntity.ok(turnoActualizado);
        }
        throw new BadRequestException("No se pudo actualizar el turno");
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
