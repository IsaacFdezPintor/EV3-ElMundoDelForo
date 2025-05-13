package es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.DAO;

import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.baseDatos.ConnectionBD;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.Usuario;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.UsuarioCreador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DAO para gestionar operaciones de base de datos relacionadas con usuarios de tipo "CREADOR".
 * Implementa las operaciones CRUD y autenticación de usuarios creadores.
 */
public class DAOUsuarioCreador implements IGenericDAO<UsuarioCreador> {

    private final static String INSERT_USUARIO_CREADOR = "INSERT INTO creador (nombre, apellidos, email, password, fecha_registro, tipo_usuario) VALUES (?, ?, ?, ?, ?, ?)";
    private final static String UPDATE_USUARIO_CREADOR = "UPDATE creador SET nombre = ?, apellidos = ?, password = ? WHERE email = ?";
    private final static String DELETE_USUARIO_CREADOR = "DELETE FROM creador WHERE email = ? AND tipo_usuario = 'CREADOR'";
    private final static String FIND_BY_CORREO = "SELECT * FROM creador WHERE email = ? AND tipo_usuario = 'CREADOR'";
    private final static String FIND_ALL = "SELECT * FROM creador WHERE tipo_usuario = 'CREADOR'";
    private final static String SQL_FIND_BY_EMAIL_BY_PASSWORD = "SELECT * FROM creador WHERE email = ? AND password = ? AND tipo_usuario = 'CREADOR'";
    private final static String EXISTS_BY_EMAIL = "SELECT COUNT(*) FROM creador WHERE email = ?";
    private final static String UPDATE_NUM_FOROS = "UPDATE creador SET num_foros_creados = ? WHERE id_usuario = ?";

    /**
     * Inserta un nuevo usuario creador en la base de datos.
     *
     * @param usuario UsuarioCreador a insertar.
     * @return Usuario insertado si fue exitoso, o null si ya existe.
     * @throws SQLException si ocurre un error de base de datos.
     */
    @Override
    public UsuarioCreador insert(UsuarioCreador usuario) throws SQLException {
        if ((usuario != null) && findByCorreo(usuario.getEmail()) == null) {
            Connection con = ConnectionBD.getConnection();
            try (PreparedStatement pst = con.prepareStatement(INSERT_USUARIO_CREADOR)) {
                pst.setString(1, usuario.getNombre());
                pst.setString(2, usuario.getApellidos());
                pst.setString(3, usuario.getEmail());
                pst.setString(4, usuario.getPassword());
                pst.setDate(5, usuario.getFechaDeRegistro());
                pst.setString(6, "CREADOR");
                pst.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return usuario;
    }

    /**
     * Actualiza los datos de un usuario creador.
     *
     * @param usuarioNuevo Datos nuevos a actualizar.
     * @param usuarioActual Usuario actual en la base de datos (usado para ubicar el registro).
     * @return true si la actualización fue exitosa, false en caso contrario.
     * @throws SQLException si ocurre un error de base de datos.
     */
    @Override
    public boolean update(UsuarioCreador usuarioNuevo, UsuarioCreador usuarioActual) throws SQLException {
        boolean result = false;
        if ((usuarioNuevo != null) && (usuarioActual != null) && findByCorreo(usuarioActual.getEmail()) != null) {
            try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(UPDATE_USUARIO_CREADOR)) {
                pst.setString(1, usuarioNuevo.getNombre());
                pst.setString(2, usuarioNuevo.getApellidos());
                pst.setString(3, usuarioNuevo.getPassword());
                pst.setString(4, usuarioActual.getEmail());
                pst.executeUpdate();
                result = true;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    /**
     * Elimina un usuario creador de la base de datos.
     *
     * @param usuario Usuario a eliminar.
     * @return true si la eliminación fue exitosa, false si no se encontró el usuario.
     * @throws SQLException si ocurre un error de base de datos.
     */
    @Override
    public boolean delete(UsuarioCreador usuario) throws SQLException {
        boolean deleted = false;
        if ((usuario != null) && findByCorreo(usuario.getEmail()) != null) {
            try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(DELETE_USUARIO_CREADOR)) {
                pst.setString(1, usuario.getEmail());
                pst.executeUpdate();
                deleted = true;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return deleted;
    }

    /**
     * Busca un usuario creador por su correo electrónico.
     *
     * @param email Correo electrónico del usuario.
     * @return UsuarioCreador si existe, o null si no se encuentra.
     * @throws SQLException si ocurre un error de base de datos.
     */
    @Override
    public UsuarioCreador findByCorreo(String email) throws SQLException {
        UsuarioCreador usuario = null;
        try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(FIND_BY_CORREO)) {
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                usuario = new UsuarioCreador();
                usuario.setId_Usuario(rs.getInt("id_usuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellidos(rs.getString("apellidos"));
                usuario.setEmail(rs.getString("email"));
                usuario.setPassword(rs.getString("password"));
                usuario.setFechaDeRegistro(rs.getDate("fecha_registro"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }

    /**
     * Recupera todos los usuarios creadores registrados en la base de datos.
     *
     * @return Lista de usuarios de tipo "CREADOR".
     * @throws SQLException si ocurre un error de base de datos.
     */
    @Override
    public List<UsuarioCreador> findAll() throws SQLException {
        List<UsuarioCreador> usuarios = new ArrayList<>();
        Connection con = ConnectionBD.getConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(FIND_ALL);
            while (rs.next()) {
                UsuarioCreador usuario = new UsuarioCreador();
                usuario.setId_Usuario(rs.getInt("id_usuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellidos(rs.getString("apellidos"));
                usuario.setEmail(rs.getString("email"));
                usuario.setPassword(rs.getString("password"));
                usuario.setFechaDeRegistro(rs.getDate("fecha_registro"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    /**
     * Verifica si un usuario con el email y contraseña proporcionados existe.
     *
     * @param email Correo electrónico del usuario.
     * @param password Contraseña del usuario.
     * @return true si el usuario existe y es de tipo "CREADOR", false en caso contrario.
     * @throws SQLException si ocurre un error de base de datos.
     */
    @Override
    public boolean check(String email, String password) throws SQLException {
        try (Connection con = ConnectionBD.getConnection()) {
            PreparedStatement pst = con.prepareStatement(SQL_FIND_BY_EMAIL_BY_PASSWORD);
            pst.setString(1, email);
            pst.setString(2, password);
            try (ResultSet rs = pst.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Verifica si existe un usuario con el correo especificado.
     *
     * @param email Correo electrónico a verificar.
     * @return true si el correo ya está registrado, false en caso contrario.
     * @throws SQLException si ocurre un error de base de datos.
     */
    @Override
    public boolean existsByEmail(String email) throws SQLException {
        Connection con = ConnectionBD.getConnection();
        try {
            PreparedStatement pst = con.prepareStatement(EXISTS_BY_EMAIL);
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateNumForos (UsuarioCreador creador) throws SQLException{
        Boolean update = false;
        Connection con = ConnectionBD.getConnection();
        try (PreparedStatement pst = con.prepareStatement(UPDATE_NUM_FOROS)) {
            pst.setInt(1, creador.getNum_ForosCreados());
            pst.setInt(2, creador.getId_Usuario());
            pst.executeUpdate();
            update = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return update;
    }
}
