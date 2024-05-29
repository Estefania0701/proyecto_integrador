package backendc3.ClinicaOdontologica.dao;


import backendc3.ClinicaOdontologica.model.Domicilio;
import backendc3.ClinicaOdontologica.model.Paciente;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PacienteDAOH2 implements IDao<Paciente> {

    private static final Logger logger = Logger.getLogger(PacienteDAOH2.class);

    private static final String SQL_SELECT_ONE = "SELECT * FROM PACIENTES WHERE ID = ?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM PACIENTES";
    private static final String SQL_SELECT_BY_EMAIL = "SELECT * FROM PACIENTES WHERE EMAIL = ?";
    private static final String SQL_INSERT = "INSERT INTO PACIENTES (NOMBRE, APELLIDO, CEDULA, FECHA_INGRESO, DOMICILIO_ID, EMAIL) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE PACIENTES SET NOMBRE = ?, APELLIDO = ?, CEDULA = ?, FECHA_INGRESO = ?, DOMICILIO_ID = ?, EMAIL = ? WHERE ID = ?";
    private static final String SQL_DELETE = "DELETE FROM PACIENTES WHERE ID = ?";

    public PacienteDAOH2() {
        DataBase.crearTablas();
    }


    @Override
    public Paciente guardar(Paciente paciente) {
        logger.info("::: Guardando paciente :::");
        Connection connection = null;
        DomicilioDAOH2 domicilioDAOH2 = new DomicilioDAOH2();
        Domicilio domicilio = domicilioDAOH2.guardar(paciente.getDomicilio());
        try {
            connection = DataBase.getConnection();
            PreparedStatement psInsert = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            psInsert.setString(1, paciente.getNombre());
            psInsert.setString(2, paciente.getApellido());
            psInsert.setString(3, paciente.getCedula());
            psInsert.setDate(4, Date.valueOf(paciente.getFechaIngreso()));
            psInsert.setInt(5, domicilio.getId());
            psInsert.setString(6, paciente.getEmail());
            psInsert.execute();
            // generar key
            ResultSet rs = psInsert.getGeneratedKeys();
            if (rs.next()) {
                paciente.setId(rs.getInt(1));
            }

        } catch (Exception e) {
            logger.error("Error al guardar el paciente", e);
        }
        logger.info("::: Paciente guardado :::");
        return paciente;
    }

    @Override
    public Paciente buscarPorId(Integer id) {
        logger.info("::: Buscando paciente por ID :::");
        Connection connection = null;
        Paciente paciente = null;
        Domicilio domicilio = null;
        try {
            connection = DataBase.getConnection();
            PreparedStatement psSelectOne = connection.prepareStatement(SQL_SELECT_ONE);
            psSelectOne.setInt(1, id);

            // guardar el resultado
            ResultSet pacienteQuery = psSelectOne.executeQuery();

            DomicilioDAOH2 domicilioDAOH2 = new DomicilioDAOH2();
            while (pacienteQuery.next()) {
                domicilio = domicilioDAOH2.buscarPorId(pacienteQuery.getInt(6));
                paciente = new Paciente(
                        pacienteQuery.getInt(1),
                        pacienteQuery.getString(2),
                        pacienteQuery.getString(3),
                        pacienteQuery.getString(4),
                        pacienteQuery.getDate(5).toLocalDate(),
                        domicilio,
                        pacienteQuery.getString(7)
                );
            }
        } catch (Exception e) {
            logger.error("Error al buscar el paciente por ID", e);
        }
        return paciente;
    }

    @Override
    public void eliminar(Integer id) {
        logger.info("::: Eliminando paciente :::");
        Connection connection = null;
        try {
            connection = DataBase.getConnection();
            PreparedStatement psDelete = connection.prepareStatement(SQL_DELETE);
            psDelete.setInt(1, id);
            psDelete.execute();
        } catch (Exception e) {
            logger.error("Error al eliminar el paciente", e);
        }
    }

    @Override
    public void actualizar(Paciente paciente) {
        logger.info("::: Actualizando paciente :::");
        Connection connection = null;
        DomicilioDAOH2 domicilioDAOH2 = new DomicilioDAOH2();
        try {
            connection = DataBase.getConnection();
            domicilioDAOH2.actualizar(paciente.getDomicilio());
            PreparedStatement psUpdate = connection.prepareStatement(SQL_UPDATE);
            psUpdate.setString(1, paciente.getNombre());
            psUpdate.setString(2, paciente.getApellido());
            psUpdate.setString(3, paciente.getCedula());
            psUpdate.setDate(4, Date.valueOf(paciente.getFechaIngreso()));
            psUpdate.setInt(5, paciente.getDomicilio().getId());
            psUpdate.setString(6, paciente.getEmail());
            psUpdate.setInt(7, paciente.getId());
            psUpdate.execute();
        } catch (Exception e) {
            logger.error("Error al actualizar el paciente", e);
        }

    }

    @Override
    public List<Paciente> buscarTodos() {
        logger.info("::: Buscando todos los pacientes :::");
        Connection connection = null;
        List<Paciente> pacientes = new ArrayList<>();
        Paciente paciente = null;
        Domicilio domicilio = null;
        try {
            connection = DataBase.getConnection();
            PreparedStatement psSelectAll = connection.prepareStatement(SQL_SELECT_ALL);

            // guardar el resultado
            ResultSet pacienteQuery = psSelectAll.executeQuery();

            DomicilioDAOH2 domicilioDAOH2 = new DomicilioDAOH2();
            while (pacienteQuery.next()) {
                domicilio = domicilioDAOH2.buscarPorId(pacienteQuery.getInt(6));
                paciente = new Paciente(
                        pacienteQuery.getInt(1),
                        pacienteQuery.getString(2),
                        pacienteQuery.getString(3),
                        pacienteQuery.getString(4),
                        pacienteQuery.getDate(5).toLocalDate(),
                        domicilio,
                        pacienteQuery.getString(7)
                );
                pacientes.add(paciente);
            }
        } catch (Exception e) {
            logger.error("Error al buscar todos los pacientes", e);
        }
        return pacientes;
    }

    @Override
    public Paciente buscarPorString(String string) {
        logger.info("::: Buscando paciente por email " + string + " :::");
        Connection connection = null;
        Paciente paciente = null;
        Domicilio domicilio = null;
        DomicilioDAOH2 domicilioDAOH2 = new DomicilioDAOH2();
        try {
            connection = DataBase.getConnection();
            PreparedStatement psSelectOne = connection.prepareStatement(SQL_SELECT_BY_EMAIL);
            psSelectOne.setString(1, string);

            // guardar el resultado
            ResultSet pacienteQuery = psSelectOne.executeQuery();

            while (pacienteQuery.next()) {
                domicilio = domicilioDAOH2.buscarPorId(pacienteQuery.getInt(6));
                paciente = new Paciente(
                        pacienteQuery.getInt(1),
                        pacienteQuery.getString(2),
                        pacienteQuery.getString(3),
                        pacienteQuery.getString(4),
                        pacienteQuery.getDate(5).toLocalDate(),
                        domicilio,
                        pacienteQuery.getString(7)
                );
            }
        } catch (Exception e) {
            logger.error("Error al buscar el paciente por email", e);
        }
        logger.info("::: Paciente encontrado :::");
        return paciente;
    }
}
