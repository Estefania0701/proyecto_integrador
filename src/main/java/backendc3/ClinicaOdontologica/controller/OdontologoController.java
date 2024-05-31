package backendc3.ClinicaOdontologica.controller;


import backendc3.ClinicaOdontologica.model.Odontologo;
import backendc3.ClinicaOdontologica.service.OdontologoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    private OdontologoService odontologoService;

    public OdontologoController() {
        this.odontologoService = new OdontologoService();
    }

    @GetMapping()
    public Odontologo buscarOdontologoPorMatricula(@RequestParam String matricula) {
        return odontologoService.buscarPorMatricula(matricula);
    }

    @GetMapping("/")
    public ResponseEntity<List<Odontologo>> listarOdontologos() {
        return ResponseEntity.ok(odontologoService.buscarTodos());
    }

    @GetMapping("/{id}")
    public Odontologo buscarOdontologoPorId(
            @PathVariable Integer id) {
        return odontologoService.buscarPorId(id);
    }

    @PostMapping("/")
    public ResponseEntity<Odontologo> registrarOdontologo(
            @RequestBody Odontologo odontologo) {
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }

    @PutMapping("/")
    public ResponseEntity<Odontologo> actualizarOdontologo(
            @RequestBody Odontologo odontologo) {
        Odontologo odontologoEncontrado = odontologoService.buscarPorId(odontologo.getId());
        if (odontologoEncontrado == null) {
            return ResponseEntity.notFound().build();
        }
        odontologoService.actualizarOdontologo(odontologo);
        return ResponseEntity.ok(odontologo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(
            @PathVariable Integer id) {
        Odontologo odontologo = odontologoService.buscarPorId(id);
        if (odontologo == null) {
            return ResponseEntity.notFound().build();
        }
        odontologoService.eliminarOdontologo(id);
        return ResponseEntity.ok("{}");
    }

}
