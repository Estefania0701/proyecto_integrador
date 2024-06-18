package backendc3.ClinicaOdontologica.controller;


import backendc3.ClinicaOdontologica.entity.Odontologo;
import backendc3.ClinicaOdontologica.exception.ResourceNotFoundException;
import backendc3.ClinicaOdontologica.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    @Autowired
    private OdontologoService odontologoService;


    @GetMapping
    public ResponseEntity<List<Odontologo>> listar() {
        List<Odontologo> odontologos = odontologoService.buscarTodos();
        return ResponseEntity.ok(odontologos);
    }
    @GetMapping("/{id}")
    public Odontologo buscarPorId(
            @PathVariable Long id) {
        return odontologoService.buscarPorId(id);
    }

    @GetMapping("/buscar")
    public Odontologo buscarPorMatricula(@RequestParam String matricula) {
        return odontologoService.buscarPorMatricula(matricula);
    }


    @PostMapping
    public ResponseEntity<Odontologo> guardar(
            @RequestBody Odontologo odontologo) {
        return ResponseEntity.ok(odontologoService.guardar(odontologo));
    }

    @PutMapping
    public ResponseEntity<Odontologo> actualizar(
            @RequestBody Odontologo odontologo) {
        Odontologo odontologoEncontrado = odontologoService.buscarPorId(odontologo.getId());
        if (odontologoEncontrado == null) {
            return ResponseEntity.badRequest().build();
        }
        odontologoService.actualizar(odontologo);
        return ResponseEntity.ok(odontologo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(
            @PathVariable Long id) throws ResourceNotFoundException {
        boolean eliminado = odontologoService.eliminar(id);
        if (eliminado) {
            return ResponseEntity.ok("Odontologo eliminado");
        }
        throw new ResourceNotFoundException("Odontologo no encontrado");
    }

}
