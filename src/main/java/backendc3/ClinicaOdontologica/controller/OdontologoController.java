package backendc3.ClinicaOdontologica.controller;


import backendc3.ClinicaOdontologica.entity.Odontologo;
import backendc3.ClinicaOdontologica.exception.BadRequestException;
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
    public ResponseEntity<Odontologo> buscarPorId(
            @PathVariable Long id) throws ResourceNotFoundException {
        Odontologo odontologo = odontologoService.buscarPorId(id);
        if (odontologo != null) {
            return ResponseEntity.ok(odontologo);
        }
        throw new ResourceNotFoundException("Odontologo no encontrado");

    }

    @GetMapping("/buscar")
    public ResponseEntity<Odontologo> buscarPorMatricula(@RequestParam String matricula) throws ResourceNotFoundException {
        Odontologo odontologo = odontologoService.buscarPorMatricula(matricula);
        if (odontologo != null) {
            return ResponseEntity.ok(odontologo);
        }
        throw new ResourceNotFoundException("Odontologo no encontrado");
    }


    @PostMapping
    public ResponseEntity<Odontologo> guardar(
            @RequestBody Odontologo odontologo) {
        return ResponseEntity.ok(odontologoService.guardar(odontologo));
    }

    @PutMapping
    public ResponseEntity<Odontologo> actualizar(
            @RequestBody Odontologo odontologo) throws BadRequestException {
        Odontologo odontologoEncontrado = odontologoService.buscarPorId(odontologo.getId());
        if (odontologoEncontrado != null) {
            return ResponseEntity.ok(odontologoService.actualizar(odontologo));
        }
        throw new BadRequestException("No se pudo actualizar el odontologo");
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
