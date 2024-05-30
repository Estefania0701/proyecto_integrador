package backendc3.ClinicaOdontologica.dao;

import backendc3.ClinicaOdontologica.model.Turno;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class TurnoDAO implements IDao<Turno> {

    private static final Logger logger = Logger.getLogger(TurnoDAO.class);

    private List<Turno> turnos = new ArrayList<>();

    @Override
    public Turno guardar(Turno turno) {
        logger.info("::: Guardando turno :::");
        PacienteDAO pacienteDAO = new PacienteDAO();
        OdontologoDAO odontologoDAO = new OdontologoDAO();
        turno.setPaciente(pacienteDAO.buscarPorId(turno.getPaciente().getId()));
        turno.setOdontologo(odontologoDAO.buscarPorId(turno.getOdontologo().getId()));

        turnos.add(turno);
        return turno;
    }

    @Override
    public Turno buscarPorId(Integer id) {
        logger.info("::: Buscando turno por ID :::");
        for (Turno turno : turnos) {
            if (turno.getId().equals(id)) {
                return turno;
            }
        }
        logger.error("No se encontró el turno");
        return null;
    }

    @Override
    public void eliminar(Integer id) {
        logger.info("::: Eliminando turno :::");
        Turno turno = buscarPorId(id);
        if (turno != null) {
            turnos.remove(turno);
        } else {
            logger.error("No se encontró el turno");
        }
    }

    @Override
    public void actualizar(Turno turno) {
        logger.info("::: Actualizando turno :::");
        Turno turnoEncontrado = buscarPorId(turno.getId());
        if (turnoEncontrado != null) {
            turnos.remove(turnoEncontrado);
            turnos.add(turno);
        } else {
            logger.error("No se encontró el turno");
        }
    }

    @Override
    public List<Turno> buscarTodos() {
        logger.info("::: Buscando todos los turnos :::");
        return turnos;
    }

    @Override
    public Turno buscarPorString(String valor) {
        logger.info("::: Buscando turno por valor :::");
        for (Turno turno : turnos) {
            if (turno.toString().contains(valor)) {
                return turno;
            }
        }
        logger.error("No se encontró el turno");
        return null;
    }
}
