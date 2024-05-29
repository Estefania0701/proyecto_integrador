package backendc3.ClinicaOdontologica.controller;

import backendc3.ClinicaOdontologica.model.Paciente;
import backendc3.ClinicaOdontologica.service.PacienteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private PacienteService pacienteService;

    public PacienteController() {
        this.pacienteService = new PacienteService();
    }

    @GetMapping()
    public Paciente buscarPacientePorEmail(@RequestParam String email) {
        return pacienteService.buscarPorEmail(email);
    }

    @GetMapping("/")
    public List<Paciente> listarPacientes() {
        return pacienteService.listarPacientes();
    }

    @GetMapping("/{id}")
    public Paciente buscarPacientePorId(
            @PathVariable Integer id) {
        return pacienteService.buscarPorId(id);
    }

    @PostMapping("/guardar")
    public Paciente guardarPaciente(
            @RequestBody Paciente paciente) {
        return pacienteService.guardarPaciente(paciente);
    }

    @PutMapping("/actualizar")
    public String actualizarPaciente(
            @RequestBody Paciente paciente) {
        Paciente pacienteBuscado = pacienteService.buscarPorId(paciente.getId());
        if (pacienteBuscado != null) {
            pacienteService.actualizarPaciente(paciente);
            return "Paciente actualizado";
        }
        return "Paciente no encontrado";
    }

    @DeleteMapping("/eliminar/{id}")
    public String eliminarPaciente(@PathVariable Integer id) {
        Paciente paciente = pacienteService.buscarPorId(id);
        if (paciente != null) {
            pacienteService.eliminarPaciente(id);
            return "Paciente eliminado";
        }
        return "Paciente no encontrado";
    }

}
