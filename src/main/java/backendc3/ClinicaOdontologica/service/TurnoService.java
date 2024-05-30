package backendc3.ClinicaOdontologica.service;

import backendc3.ClinicaOdontologica.dao.IDao;
import backendc3.ClinicaOdontologica.dao.TurnoDAO;
import backendc3.ClinicaOdontologica.model.Turno;

import java.util.List;

public class TurnoService {

    private IDao<Turno> turnoDAO;

    public TurnoService() {
        this.turnoDAO = new TurnoDAO();
    }

    public Turno buscarTurnoPorId(Integer id) {
        return turnoDAO.buscarPorId(id);
    }

    public List<Turno> buscarTodos() {
        return turnoDAO.buscarTodos();
    }

    public Turno guardarTurno(Turno turno) {
        return turnoDAO.guardar(turno);
    }

    public void actualizarTurno(Turno turno) {
        turnoDAO.actualizar(turno);
    }

    public void eliminarTurno(Integer id) {
        turnoDAO.eliminar(id);
    }



}
