package backendc3.ClinicaOdontologica.service;

import backendc3.ClinicaOdontologica.dao.IDao;
import backendc3.ClinicaOdontologica.dao.TurnosDAOLista;
import backendc3.ClinicaOdontologica.model.Turno;

import java.util.List;

public class TurnoService {

    private IDao<Turno> turnoDAO;

    public TurnoService() {
        this.turnoDAO = new TurnosDAOLista();
    }

    public Turno buscarTurnoPorId(Integer id) {
        return turnoDAO.buscarPorId(id);
    }

    public Turno guardarTurno(Turno turno) {
        return turnoDAO.guardar(turno);
    }

    public List<Turno> buscarTodos() {
        return turnoDAO.buscarTodos();
    }



}
