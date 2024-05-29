package backendc3.ClinicaOdontologica.service;

;

import backendc3.ClinicaOdontologica.dao.IDao;
import backendc3.ClinicaOdontologica.dao.OdontologoDAO;
import backendc3.ClinicaOdontologica.model.Odontologo;

import java.util.List;

public class OdontologoService {

    private IDao<Odontologo> odontologoDAO;

    public OdontologoService() {
        this.odontologoDAO = new OdontologoDAO();
    }

    public Odontologo guardarOdontologo(Odontologo odontologo) {
        odontologoDAO.guardar(odontologo);
        return odontologo;
    }

    public Odontologo buscarPorId(Integer id) {
        return odontologoDAO.buscarPorId(id);
    }

    public Odontologo buscarPorMatricula(String matricula) {
        return odontologoDAO.buscarPorString(matricula);
    }

    public void actualizarOdontologo(Odontologo odontologo) {
        odontologoDAO.actualizar(odontologo);
    }

    public void eliminarOdontologo(Integer id) {
        odontologoDAO.eliminar(id);
    }

    public List<Odontologo> buscarTodos() {
        return odontologoDAO.buscarTodos();
    }

}
