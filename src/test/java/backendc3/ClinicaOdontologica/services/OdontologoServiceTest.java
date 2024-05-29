package backendc3.ClinicaOdontologica.services;


import backendc3.ClinicaOdontologica.dao.DataBase;
import backendc3.ClinicaOdontologica.model.Odontologo;
import backendc3.ClinicaOdontologica.service.OdontologoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OdontologoServiceTest {

    @Test
    public void guardarOdonotologoEnH2() {
        Odontologo odontologo = new Odontologo(
                "123456",
            "Juan",
            "Perez"
        );

        DataBase.crearTablas();
        OdontologoService odontologoService = new OdontologoService();
        Odontologo odontologoGuardado = odontologoService.guardarOdontologo(odontologo);

        Assertions.assertTrue(odontologoGuardado != null);
    }

    @Test
    public void buscarTodosEnH2() {
        DataBase.crearTablas();
        OdontologoService odontologoService = new OdontologoService();
        Assertions.assertTrue(odontologoService.buscarTodos().size() == 2);
    }

}
