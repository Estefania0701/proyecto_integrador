package backendc3.ClinicaOdontologica.service;


import backendc3.ClinicaOdontologica.dao.IDao;
import backendc3.ClinicaOdontologica.dao.PacienteDAOH2;
import backendc3.ClinicaOdontologica.model.Paciente;

import java.util.List;

public class PacienteService {
    private IDao<Paciente> pacienteDAO;

    public PacienteService() {
        this.pacienteDAO = new PacienteDAOH2();
    }

    public Paciente guardarPaciente(Paciente paciente) {
        return pacienteDAO.guardar(paciente);
    }

    public Paciente buscarPorId(Integer id) {
        return pacienteDAO.buscarPorId(id);
    }

    public Paciente buscarPorEmail(String email) {
        return pacienteDAO.buscarPorString(email);
    }

    public void actualizarPaciente(Paciente paciente) {
        pacienteDAO.actualizar(paciente);
    }

    public void eliminarPaciente(Integer id) {
        pacienteDAO.eliminar(id);
    }

    public List<Paciente> listarPacientes() {
        return pacienteDAO.buscarTodos();
    }
}
