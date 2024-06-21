package backendc3.ClinicaOdontologica.service;

import backendc3.ClinicaOdontologica.dto.TurnoDTO;
import backendc3.ClinicaOdontologica.entity.Odontologo;
import backendc3.ClinicaOdontologica.entity.Paciente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class TurnoServiceTest {

    @Autowired
    private TurnoService turnoService;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private OdontologoService odontologoService;

    @Autowired
    private MockMvc mockMvc;

    private void cargarDatos() {
        Paciente paciente = pacienteService.guardar(new Paciente("Estefanía", "Aguas", 1223231, LocalDate.now(), "estefa@mail.com", null));
        Odontologo odontologo = odontologoService.guardar(new Odontologo("Juan", "Perez", "asd21321"));
        turnoService.guardar(new TurnoDTO(LocalDate.now(), paciente.getId(), odontologo.getId()));
    }

    @Test
    void buscarPorId() {
    }

    @Test
    void buscarTodos() throws Exception {
        cargarDatos();
        MvcResult respuesta = mockMvc.perform(MockMvcRequestBuilders.get("/turnos")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }

    @Test
    void guardar() {
    }

    @Test
    void actualizar() {
    }

    @Test
    void eliminar() {
    }

}