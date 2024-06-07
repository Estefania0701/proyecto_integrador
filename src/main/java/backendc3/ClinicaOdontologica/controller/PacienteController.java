package backendc3.ClinicaOdontologica.controller;

import backendc3.ClinicaOdontologica.entity.Paciente;
import backendc3.ClinicaOdontologica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService service;

    @GetMapping
    public ResponseEntity<List<Paciente>> listar() {
        List<Paciente> pacientes = service.buscarTodos();
        return ResponseEntity.ok(pacientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPorId(@PathVariable Long id) {
        Paciente pacienteEncontrado = service.buscarPorId(id);
        if (pacienteEncontrado != null) {
            return ResponseEntity.ok(pacienteEncontrado);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<Paciente> buscarPorEmail(@RequestParam String email) {
        Paciente pacienteEncontrado = service.buscarPorEmail(email);
        if (pacienteEncontrado != null) {
            return ResponseEntity.ok(pacienteEncontrado);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Paciente> guardarPaciente(
            @RequestBody Paciente paciente) {
        Paciente pacienteGuardado = service.guardar(paciente);
        return ResponseEntity.ok(pacienteGuardado);
    }

    @PutMapping
    public ResponseEntity<Paciente> actualizarPaciente(
            @RequestBody Paciente paciente) {
        Paciente pacienteActualizado = service.actualizar(paciente);
        if (pacienteActualizado != null) {
            return ResponseEntity.ok(pacienteActualizado);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) {
        boolean eliminado = service.eliminar(id);
        if (eliminado) {
            return ResponseEntity.ok("Paciente eliminado");
        }
        return ResponseEntity.badRequest().build();
    }

}
