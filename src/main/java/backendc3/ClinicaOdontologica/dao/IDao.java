package backendc3.ClinicaOdontologica.dao;

import java.util.List;

public interface IDao<T> {

    T guardar (T t);

    T buscarPorId (Integer id);

    void eliminar (Integer id);

    void actualizar (T t);

    List<T> buscarTodos();

    T buscarPorString(String valor);
}
