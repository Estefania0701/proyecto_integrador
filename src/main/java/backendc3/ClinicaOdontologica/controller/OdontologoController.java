package backendc3.ClinicaOdontologica.controller;


import backendc3.ClinicaOdontologica.model.Odontologo;
import backendc3.ClinicaOdontologica.service.OdontologoService;
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
    public List<Odontologo> listarOdontologos() {
        return odontologoService.buscarTodos();
    }

    @GetMapping("/{id}")
    public Odontologo buscarOdontologoPorId(
            @PathVariable Integer id) {
        return odontologoService.buscarPorId(id);
    }

    @PostMapping("/guardar")
    public Odontologo guardarOdontologo(
            @RequestBody Odontologo odontologo) {
        return odontologoService.guardarOdontologo(odontologo);
    }

    @PutMapping("/actualizar")
    public String actualizarOdontologo(
            @RequestBody Odontologo odontologo) {
        Odontologo odontologoBuscado = odontologoService.buscarPorId(odontologo.getId());
        if (odontologoBuscado != null) {
            odontologoService.actualizarOdontologo(odontologo);
            return "Odontologo actualizado";
        }
        return "Odontologo no encontrado";
    }

    @DeleteMapping("/eliminar/{id}")
    public String eliminarOdontologo(@PathVariable Integer id) {
        Odontologo odontologo = odontologoService.buscarPorId(id);
        if (odontologo != null) {
            odontologoService.eliminarOdontologo(id);
            return "Odontologo eliminado";
        }
        return "Odontologo no encontrado";
    }

}
