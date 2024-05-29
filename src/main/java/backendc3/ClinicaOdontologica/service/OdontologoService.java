package backendc3.ClinicaOdontologica.service;

;

import backendc3.ClinicaOdontologica.dao.IDao;
import backendc3.ClinicaOdontologica.model.Odontologo;

import java.util.List;

public class OdontologoService {

    private IDao<Odontologo> odontologoDAOH2;

    public Odontologo guardarOdontologoEnH2(Odontologo odontologo) {
        odontologoDAOH2.guardar(odontologo);
        return odontologo;
    }

    public List<Odontologo> buscarTodosEnH2() {
        return odontologoDAOH2.buscarTodos();
    }


}
