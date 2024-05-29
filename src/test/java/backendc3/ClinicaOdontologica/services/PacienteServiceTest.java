package backendc3.ClinicaOdontologica.services;

import backendc3.ClinicaOdontologica.dao.DataBase;
import backendc3.ClinicaOdontologica.model.Paciente;
import backendc3.ClinicaOdontologica.service.PacienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class PacienteServiceTest {

    @Test
    public void buscarPorIdPaciente() {
        DataBase.crearTablas();
        PacienteService pacienteService = new PacienteService();
        Integer id = 2;
        Paciente paciente = pacienteService.buscarPorId(id);
        Assertions.assertTrue(paciente != null);
    }

    @Test
    public void buscarPorEmailPaciente() {
        DataBase.crearTablas();
        PacienteService pacienteService = new PacienteService();
        String email = "a";
    }
}