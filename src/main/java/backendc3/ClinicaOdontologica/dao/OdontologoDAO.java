package backendc3.ClinicaOdontologica.dao;

import backendc3.ClinicaOdontologica.model.Odontologo;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDAO implements IDao<Odontologo> {

    private static final Logger logger = Logger.getLogger(OdontologoDAO.class);
    private static final String SQL_INSERT = "INSERT INTO ODONTOLOGOS (MATRICULA, NOMBRE, APELLIDO) VALUES (?, ?, ?)";
    private static final String SQL_SELECT_ALL = "SELECT * FROM ODONTOLOGOS";
    private static final String SQL_SELECT_ONE = "SELECT * FROM ODONTOLOGOS WHERE ID = ?";
    private static final String SQL_SELECT_BY_MATRICULA = "SELECT * FROM ODONTOLOGOS WHERE MATRICULA = ?";
    private static final String SQL_DELETE = "DELETE FROM ODONTOLOGOS WHERE ID = ?";
    private static final String SQL_UPDATE = "UPDATE ODONTOLOGOS SET MATRICULA = ?, NOMBRE = ?, APELLIDO = ? WHERE ID = ?";


    @Override
    public Odontologo guardar(Odontologo odontologo) {
        logger.info("::: Guardando odontologo :::");
        Connection connection = null;
        try {
            connection = DataBase.getConnection();
            PreparedStatement psInsert = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            psInsert.setString(1, odontologo.getMatricula());
            psInsert.setString(2, odontologo.getNombre());
            psInsert.setString(3, odontologo.getApellido());
            psInsert.execute();
            // generar key
            ResultSet rs = psInsert.getGeneratedKeys();
            if (rs.next()) {
                odontologo.setId(rs.getInt(1));
            }

        } catch (Exception e) {
            logger.error("Error al guardar el odontologo", e);
        }
        return odontologo;
    }

    @Override
    public Odontologo buscarPorId(Integer id) {
        logger.info("::: Buscando odontologo por ID :::");
        Connection connection = null;
        Odontologo odontologo = null;
        try {
            connection = DataBase.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_SELECT_ONE);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                odontologo = new Odontologo(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                );
            }
        } catch (Exception e) {
            logger.error("Error al buscar el odontologo por ID", e);
        }
        return odontologo;
    }

    @Override
    public void eliminar(Integer id) {
        logger.info("::: Eliminando odontologo por ID :::");
        Connection connection = null;
        try {
            connection = DataBase.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_DELETE);
            ps.setInt(1, id);
            ps.execute();
        } catch (Exception e) {
            logger.error("Error al eliminar el odontologo por ID", e);
        }
    }

    @Override
    public void actualizar(Odontologo odontologo) {
        logger.info("::: Actualizando odontologo por ID :::");
        Connection connection = null;
        try {
            connection = DataBase.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_UPDATE);
            ps.setString(1, odontologo.getMatricula());
            ps.setString(2, odontologo.getNombre());
            ps.setString(3, odontologo.getApellido());
            ps.setInt(4, odontologo.getId());
            ps.execute();
        } catch (Exception e) {
            logger.error("Error al actualizar el odontologo por ID", e);
        }
    }

    @Override
    public List<Odontologo> buscarTodos() {
        logger.info("::: Buscando todos los odontologos :::");
        Connection connection = null;
        List<Odontologo> odontologos = new ArrayList<>();
        try {
            connection = DataBase.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SQL_SELECT_ALL);
            while (rs.next()) {
                Odontologo odontologo = new Odontologo(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                );
                odontologos.add(odontologo);
            }
        } catch (Exception e) {
            logger.error("Error al buscar todos los odontologos", e);
        }
        return odontologos;
    }

    @Override
    public Odontologo buscarPorString(String valor) {
        logger.info("::: Buscando odontologo por matricula :::");
        Connection connection = null;
        Odontologo odontologo = null;
        try {
            connection = DataBase.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_SELECT_BY_MATRICULA);
            ps.setString(1, valor);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                odontologo = new Odontologo(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                );
            }
        } catch (Exception e) {
            logger.error("Error al buscar el odontologo por matricula", e);
        }
        return odontologo;
    }
}
