package backendc3.ClinicaOdontologica.service;

import backendc3.ClinicaOdontologica.entity.Paciente;
import backendc3.ClinicaOdontologica.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;


    public Paciente buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Paciente buscarPorEmail(String email) {
        return repository.findByEmail(email).orElse(null);
    }

    public List<Paciente> buscarTodos() {
        return repository.findAll();
    }

    public Paciente guardar(Paciente paciente) {
        return repository.save(paciente);
    }

    public Paciente actualizar(Paciente paciente) {
        Paciente pacienteExistente = buscarPorId(paciente.getId());
        if (pacienteExistente == null) {
            return null;
        }
        pacienteExistente.setNombre(paciente.getNombre());
        pacienteExistente.setApellido(paciente.getApellido());
        pacienteExistente.setEmail(paciente.getEmail());
        pacienteExistente.setFechaIngreso(paciente.getFechaIngreso());
        pacienteExistente.setDomicilio(paciente.getDomicilio());
        return repository.save(pacienteExistente);
    }

    public boolean eliminar(Long id) {
        Paciente pacienteEncontrado = buscarPorId(id);
        if (pacienteEncontrado == null) {
            return false;
        }
        repository.delete(pacienteEncontrado);
        return true;
    }
}
