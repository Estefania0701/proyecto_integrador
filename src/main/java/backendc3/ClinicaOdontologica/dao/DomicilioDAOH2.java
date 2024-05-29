package backendc3.ClinicaOdontologica.dao;

import backendc3.ClinicaOdontologica.model.Domicilio;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class DomicilioDAOH2 implements IDao<Domicilio> {

    private static final Logger logger = Logger.getLogger(DomicilioDAOH2.class);

    private static final String SQL_SELECT_ONE = "SELECT * FROM DOMICILIOS WHERE ID = ?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM DOMICILIOS";
    private static final String SQL_INSERT = "INSERT INTO DOMICILIOS (CALLE, NUMERO,LOCALIDAD, PROVINCIA) VALUES(?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE DOMICILIOS SET CALLE = ?, NUMERO = ?, LOCALIDAD = ?, PROVINCIA = ? WHERE ID = ?";


    @Override
    public Domicilio guardar(Domicilio domicilio) {
        logger.info("::: Guardando domicilio :::");
        Connection connection = null;
        try {
            connection = DataBase.getConnection();
            PreparedStatement psInsert = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            psInsert.setString(1, domicilio.getCalle());
            psInsert.setInt(2, domicilio.getNumero());
            psInsert.setString(3, domicilio.getLocalidad());
            psInsert.setString(4, domicilio.getProvincia());
            // generar key
            psInsert.execute();
            ResultSet rs = psInsert.getGeneratedKeys();
            if (rs.next()) {
                domicilio.setId(rs.getInt(1));
            }
        } catch (Exception e) {
            logger.error("Error al guardar el domicilio", e);
        }
        logger.info("::: Domicilio guardado :::");
        return domicilio;
    }

    @Override
    public Domicilio buscarPorId(Integer id) {
        logger.info("::: Buscando domicilio por ID :::");
        Connection connection = null;
        Domicilio domicilio = null;

        try {
            connection = DataBase.getConnection();
            Statement statement = connection.createStatement();
            PreparedStatement psSelectOne = connection.prepareStatement(SQL_SELECT_ONE);
            psSelectOne.setInt(1, id);

            // guardar el resultado
            ResultSet domicilioQuery = psSelectOne.executeQuery();

            while (domicilioQuery.next()) {
                domicilio = new Domicilio(
                        domicilioQuery.getInt(1),
                        domicilioQuery.getString(2),
                        domicilioQuery.getInt(3),
                        domicilioQuery.getString(4),
                        domicilioQuery.getString(5)
                );
            }
        } catch (Exception e) {
            logger.error("Error al buscar el domicilio por ID", e);
        }
        logger.info("::: Domicilio encontrado :::");
        return domicilio;
    }

    @Override
    public void eliminar(Integer id) {

    }

    @Override
    public void actualizar(Domicilio domicilio) {
        logger.info("::: Actualizando domicilio :::");
        Connection connection = null;
        try {
            connection = DataBase.getConnection();
            PreparedStatement psUpdate = connection.prepareStatement(SQL_UPDATE);
            psUpdate.setString(1, domicilio.getCalle());
            psUpdate.setInt(2, domicilio.getNumero());
            psUpdate.setString(3, domicilio.getLocalidad());
            psUpdate.setString(4, domicilio.getProvincia());
            psUpdate.setInt(5, domicilio.getId());
            psUpdate.execute();
        } catch (Exception e) {
            logger.error("Error al actualizar el domicilio", e);
        }
    }

    @Override
    public List<Domicilio> buscarTodos() {
        return null;
    }

    @Override
    public Domicilio buscarPorString(String valor) {
        return null;
    }
}
