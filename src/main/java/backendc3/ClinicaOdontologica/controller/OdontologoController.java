package backendc3.ClinicaOdontologica.controller;


import backendc3.ClinicaOdontologica.entity.Odontologo;
import backendc3.ClinicaOdontologica.exception.BadRequestException;
import backendc3.ClinicaOdontologica.exception.ResourceNotFoundException;
import backendc3.ClinicaOdontologica.service.OdontologoService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j
@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    @Autowired
    private OdontologoService odontologoService;


    @GetMapping
    public ResponseEntity<List<Odontologo>> listar() {
        List<Odontologo> odontologos = odontologoService.buscarTodos();
        log.info("Listando odontologos");
        return ResponseEntity.ok(odontologos);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarPorId(
            @PathVariable Long id) throws ResourceNotFoundException {
        Odontologo odontologo = odontologoService.buscarPorId(id);
        if (odontologo != null) {
            log.info("Odontologo con id " + id + " encontrado");
            return ResponseEntity.ok(odontologo);
        }
        log.error("Odontologo con id " + id + " no encontrado");
        throw new ResourceNotFoundException("Odontologo con id " + id + " no encontrado");

    }

    @GetMapping("/buscar")
    public ResponseEntity<Odontologo> buscarPorMatricula(@RequestParam String matricula) throws ResourceNotFoundException {
        Odontologo odontologo = odontologoService.buscarPorMatricula(matricula);
        if (odontologo != null) {
            log.info("Odontologo con matricula " + matricula + " encontrado");
            return ResponseEntity.ok(odontologo);
        }
        log.error("Odontologo con matricula " + matricula + " no encontrado");
        throw new ResourceNotFoundException("Odontologo con matricula " + matricula + " no encontrado");
    }


    @PostMapping
    public ResponseEntity<Odontologo> guardar(
            @RequestBody Odontologo odontologo) {
        Odontologo odontologoGuardado = odontologoService.guardar(odontologo);
        log.info("Odontologo guardado");
        return ResponseEntity.ok(odontologoGuardado);
    }

    @PutMapping
    public ResponseEntity<Odontologo> actualizar(
            @RequestBody Odontologo odontologo) throws BadRequestException {
        Odontologo odontologoEncontrado = odontologoService.buscarPorId(odontologo.getId());
        if (odontologoEncontrado != null) {
            log.info("Odontologo con id " + odontologo.getId() + " actualizado");
            return ResponseEntity.ok(odontologoService.actualizar(odontologo));
        }
        log.error("No se pudo actualizar el odontologo con id " + odontologo.getId());
        throw new BadRequestException("No se pudo actualizar el odontologo con id " + odontologo.getId());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(
            @PathVariable Long id) throws ResourceNotFoundException {
        boolean eliminado = odontologoService.eliminar(id);
        if (eliminado) {
            log.info("Odontologo con id " + id + " eliminado");
            return ResponseEntity.ok("Odontologo con id " + id + " eliminado");
        }
        log.error("Odontologo con id " + id + " no encontrado");
        throw new ResourceNotFoundException("Odontologo con id " + id + " no encontrado");
    }

}
