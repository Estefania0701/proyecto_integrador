package backendc3.ClinicaOdontologica.service;

import backendc3.ClinicaOdontologica.dto.TurnoDTO;
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

    public TurnoDTO buscarPorId(Long id) {
        Turno turno = repository.findById(id).orElse(null);
        if (turno == null) {
            return null;
        }
        return turnoATurnoDTO(turno);
    }

    public List<TurnoDTO> buscarTodos() {
        List<Turno> turnos = repository.findAll();
        return turnos.stream().map(this::turnoATurnoDTO).toList();
    }

    public TurnoDTO guardar(Turno turno) {
        Paciente paciente = pacienteService.buscarPorId(turno.getPaciente().getId());
        Odontologo odontologo = odontologoService.buscarPorId(turno.getOdontologo().getId());
        if (paciente == null || odontologo == null) {
            return null;
        }
        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);
        Turno turnoGuardado = repository.save(turno);
        return turnoATurnoDTO(turnoGuardado);
    }

    public TurnoDTO actualizar(TurnoDTO turnoDTO) {
        Turno turnoExistente = repository.findById(turnoDTO.getId()).orElse(null);
        Paciente pacienteExistente = pacienteService.buscarPorId(turnoDTO.getPacienteId());
        Odontologo odontologoExistente = odontologoService.buscarPorId(turnoDTO.getOdontologoId());
        if (turnoExistente == null || pacienteExistente == null || odontologoExistente == null) {
            return null;
        }
        Turno turnoActualizado = repository.save(turnoDTOATurno(turnoDTO, pacienteExistente, odontologoExistente));
        return turnoATurnoDTO(turnoActualizado);
    }

    public boolean eliminar(Long id) {
        Turno turnoEncontrado = repository.findById(id).orElse(null);
        if (turnoEncontrado == null) {
            return false;
        }
        repository.delete(turnoEncontrado);
        return true;
    }

    public TurnoDTO turnoATurnoDTO(Turno turno) {
        TurnoDTO turnoDTO = new TurnoDTO();
        turnoDTO.setId(turno.getId());
        turnoDTO.setFecha(turno.getFecha());
        turnoDTO.setPacienteId(turno.getPaciente().getId());
        turnoDTO.setOdontologoId(turno.getOdontologo().getId());
        return turnoDTO;
    }

    public Turno turnoDTOATurno(TurnoDTO turnoDTO, Paciente paciente, Odontologo odontologo) {
        Turno turno = new Turno();
        turno.setId(turnoDTO.getId());
        turno.setFecha(turnoDTO.getFecha());

        turno.setPaciente(paciente);

        turno.setOdontologo(odontologo);
        return turno;
    }
}
