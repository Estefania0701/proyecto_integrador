package backendc3.ClinicaOdontologica.controller;

import backendc3.ClinicaOdontologica.entity.Paciente;
import backendc3.ClinicaOdontologica.exception.BadRequestException;
import backendc3.ClinicaOdontologica.exception.ResourceNotFoundException;
import backendc3.ClinicaOdontologica.service.PacienteService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Log4j
@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService service;

    @GetMapping
    public ResponseEntity<List<Paciente>> listar() {
        List<Paciente> pacientes = service.buscarTodos();
        log.info("Listando pacientes");
        return ResponseEntity.ok(pacientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPorId(@PathVariable Long id) throws ResourceNotFoundException {
        Paciente pacienteEncontrado = service.buscarPorId(id);
        if (pacienteEncontrado != null) {
            log.info("Paciente con id " + id + " encontrado");
            return ResponseEntity.ok(pacienteEncontrado);
        }
        log.error("Paciente con id " + id + " no encontrado");
        throw new ResourceNotFoundException("Paciente con id " + id + " no encontrado");
    }

    @GetMapping("/buscar")
    public ResponseEntity<Paciente> buscarPorEmail(@RequestParam String email) throws ResourceNotFoundException {
        Paciente pacienteEncontrado = service.buscarPorEmail(email);
        if (pacienteEncontrado != null) {
            log.info("Paciente con email " + email + " encontrado");
            return ResponseEntity.ok(pacienteEncontrado);
        }
        log.error("Paciente con email " + email + " no encontrado");
        throw new ResourceNotFoundException("Paciente con email " + email + " no encontrado");
    }

    @PostMapping
    public ResponseEntity<Paciente> guardarPaciente(
            @RequestBody Paciente paciente) {
        Paciente pacienteGuardado = service.guardar(paciente);
        log.info("Paciente guardado");
        return ResponseEntity.ok(pacienteGuardado);
    }

    @PutMapping
    public ResponseEntity<Paciente> actualizarPaciente(
            @RequestBody Paciente paciente) throws BadRequestException {
        Paciente pacienteActualizado = service.actualizar(paciente);
        if (pacienteActualizado != null) {
            log.info("Paciente con id " + paciente.getId() + " actualizado");
            return ResponseEntity.ok(pacienteActualizado);
        }
        log.error("No se pudo actualizar el paciente con id " + paciente.getId());
        throw new BadRequestException("No se pudo actualizar el paciente con id " + paciente.getId());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        boolean eliminado = service.eliminar(id);
        if (eliminado) {
            return ResponseEntity.ok("Paciente con id " + id + " eliminado");
        }
        log.error("Paciente con id " + id + " no encontrado");
        throw new ResourceNotFoundException("Paciente con id " + id + " no encontrado");
    }

}
