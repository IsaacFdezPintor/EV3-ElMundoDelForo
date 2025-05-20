package es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.DAO;

import java.sql.SQLException;
import java.util.List;

public interface IGenericDAO<T> {
        T insert(T usuario) throws SQLException;

    boolean update(T usuarioNuevo , T usuarioActual ) throws SQLException;

    boolean delete(T usuario) throws SQLException;

    T findByCorreo(String email) throws SQLException;

    List<T> findAll() throws SQLException;

    boolean check(String email, String password) throws SQLException;

    boolean existsByEmail(String email) throws SQLException;
}
