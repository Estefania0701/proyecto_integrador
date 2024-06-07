package backendc3.ClinicaOdontologica.service;

import backendc3.ClinicaOdontologica.entity.Odontologo;
import backendc3.ClinicaOdontologica.entity.Paciente;
import backendc3.ClinicaOdontologica.entity.Turno;
import backendc3.ClinicaOdontologica.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService {

    @Autowired
    private TurnoRepository repository;

    @Autowired
    PacienteService pacienteService;

    @Autowired
    OdontologoService odontologoService;

    public Turno buscarPorId(Long id){
        return repository.findById(id).orElse(null);
    }

    public List<Turno> buscarTodos(){
        return repository.findAll();
    }

    public Turno guardar(Turno turno){
        Paciente paciente = pacienteService.buscarPorId(turno.getPaciente().getId());
        Odontologo odontologo = odontologoService.buscarPorId(turno.getOdontologo().getId());
        if(paciente == null || odontologo == null){
            return null;
        }
        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);
        return repository.save(turno);
    }

    public Turno actualizar(Turno turno){
        Turno turnoExistente = buscarPorId(turno.getId());
        if(turnoExistente == null){
            return null;
        }
        turnoExistente.setFecha(turno.getFecha());
        return repository.save(turnoExistente);
    }

    public boolean eliminar(Long id){
        Turno turnoEncontrado = buscarPorId(id);
        if(turnoEncontrado == null){
            return false;
        }
        repository.delete(turnoEncontrado);
        return true;
    }
}
