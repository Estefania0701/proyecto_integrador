package backendc3.ClinicaOdontologica.service;

import backendc3.ClinicaOdontologica.entity.Paciente;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PacienteServiceTest {

    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
    void guardar() {
        Paciente paciente = new Paciente("Estefanía", "Aguas", 1223231, LocalDate.now(), "estefa@mail.com", null);
        Paciente pacienteGuardado = pacienteService.guardar(paciente);
        assertEquals(1L, pacienteGuardado.getId());

    }

    @Test
    @Order(2)
    void buscarPorId() {
        Long id = 1L;
        Paciente paciente = pacienteService.buscarPorId(id);
        assertNotNull(paciente);
    }

    @Test
    @Order(3)
    void actualizar() {
        Long id = 1L;
        Paciente paciente = pacienteService.buscarPorId(id);
        assertNotEquals("Valentina", paciente.getNombre());
        paciente.setNombre("Valentina");
        Paciente pacienteActualizado = pacienteService.actualizar(paciente);
        assertEquals("Valentina", pacienteActualizado.getNombre());
    }

    @Test
    @Order(4)
    void buscarTodos() {
        List<Paciente> pacientes = pacienteService.buscarTodos();
        assertEquals(1, pacientes.size());
    }

    @Test
    @Order(5)
    void eliminar() {
        Long id = 1L;
        boolean fueEliminado = pacienteService.eliminar(id);
        Paciente pacienteEliminado = pacienteService.buscarPorId(id);
        assertTrue(fueEliminado);
        assertNull(pacienteEliminado);
    }

    @Test
    void buscarPorEmail() {
    }




}