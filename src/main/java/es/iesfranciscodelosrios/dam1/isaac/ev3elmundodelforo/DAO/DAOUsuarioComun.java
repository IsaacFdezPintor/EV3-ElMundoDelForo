package es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.DAO;

import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.baseDatos.ConnectionBD;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.Participacion;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.Usuario;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.UsuarioComun;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.UsuarioCreador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOUsuarioComun implements IGenericDAO<UsuarioComun> {

    private final static String INSERT_USUARIO_COMUN = "INSERT INTO comun (nombre, apellidos, email, password, fecha_registro, tipo_usuario) VALUES (?, ?, ?, ?, ?, ?)";
    private final static String UPDATE_USUARIO_COMUN = "UPDATE comun SET nombre = ?, apellidos = ?, password = ? WHERE email = ?";
    private final static String DELETE_USUARIO_COMUN = "DELETE FROM comun WHERE email = ? AND tipo_usuario = 'COMUN'";
    private final static String FIND_BY_CORREO = "SELECT * FROM comun WHERE email = ? AND tipo_usuario = 'COMUN'";
    private final static String FIND_ALL = "SELECT * FROM comun WHERE tipo_usuario = 'COMUN'";
    private final static String SQL_FIND_BY_EMAIL_BY_PASSWORD = "SELECT * FROM comun WHERE email = ? AND password = ? AND tipo_usuario = 'COMUN'";
    private final static String EXISTS_BY_EMAIL = "SELECT COUNT(*) FROM comun WHERE email = ? AND tipo_usuario = 'COMUN'";
    private final static String UPDATE_NUM_COMENTARIOS = "UPDATE comun SET num_comentarios = ? WHERE id_usuario = ?";
    private final static String SELECT_COMENTARIOS = "SELECT num_comentarios FROM comun WHERE id_usuario = ?";
    private final static String UPDATE_PARTICIPACION = "UPDATE comun SET nivel_participacion = ? WHERE id_usuario = ?";
    private final static String SELECT_PARTICIPACION = "SELECT nivel_participacion FROM comun WHERE id_usuario = ?";

    /**
     * Inserta un nuevo usuario de tipo Comun en la base de datos.
     *
     * @param usuario El objeto UsuarioComun con los datos del nuevo usuario.
     * @return El usuario insertado, si la operación fue exitosa.
     * @throws SQLException Si ocurre un error con la base de datos.
     */
    @Override
    public UsuarioComun insert(UsuarioComun usuario) throws SQLException {
        if (usuario != null && !existsByEmail(usuario.getEmail())) {
            try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(INSERT_USUARIO_COMUN)) {
                pst.setString(1, usuario.getNombre());
                pst.setString(2, usuario.getApellidos());
                pst.setString(3, usuario.getEmail().trim());
                pst.setString(4, usuario.getPassword());
                pst.setDate(5, usuario.getFechaDeRegistro());
                pst.setString(6, "COMUN");
                pst.executeUpdate();
            } catch (SQLException e){
                throw new RuntimeException(e);
            }
        }
        return usuario;
    }

    /**
     * Actualiza los datos de un usuario de tipo Comun en la base de datos.
     *
     * @param usuarioNuevo El objeto UsuarioComun con los nuevos datos.
     * @param usuarioActual El objeto UsuarioComun con los datos actuales del usuario.
     * @return true si la actualización fue exitosa, false si no.
     * @throws SQLException Si ocurre un error con la base de datos.
     */
    @Override
    public boolean update(UsuarioComun usuarioNuevo ,UsuarioComun usuarioActual) throws SQLException {
        boolean result = false;
        if ((usuarioNuevo != null) && (usuarioActual != null) && findByCorreo(usuarioActual.getEmail()) != null) {
            try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(UPDATE_USUARIO_COMUN)) {
                pst.setString(1, usuarioNuevo.getNombre());
                pst.setString(2, usuarioNuevo.getApellidos());
                pst.setString(3, usuarioNuevo.getPassword());
                pst.setString(4, usuarioActual.getEmail());
                result = true;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    /**
     * Elimina un usuario de tipo Comun de la base de datos.
     *
     * @param usuario El objeto UsuarioComun que se desea eliminar.
     * @return true si el usuario fue eliminado, false si no.
     * @throws SQLException Si ocurre un error con la base de datos.
     */
    @Override
    public boolean delete(UsuarioComun usuario) throws SQLException {
        boolean deleted = false;

        if (usuario != null) {
            try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(DELETE_USUARIO_COMUN)) {
                pst.setString(1, usuario.getEmail());
                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                    deleted = true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new SQLException("Error al intentar eliminar el usuario: " + e.getMessage());
            }
        } else {
            System.out.println("El usuario es nulo, no se puede eliminar.");
        }
        return deleted;
    }

    /**
     * Busca un usuario de tipo Comun por su correo electrónico.
     *
     * @param email El correo electrónico del usuario a buscar.
     * @return El objeto UsuarioComun si se encuentra, null si no.
     * @throws SQLException Si ocurre un error con la base de datos.
     */
    @Override
    public UsuarioComun findByCorreo(String email) throws SQLException {

        UsuarioComun usuario = null;

        try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(FIND_BY_CORREO)) {
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                usuario = new UsuarioComun();
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
     * Recupera todos los usuarios de tipo Comun.
     *
     * @return Una lista de objetos UsuarioComun.
     * @throws SQLException Si ocurre un error con la base de datos.
     */
    @Override
    public List<UsuarioComun> findAll() throws SQLException {
        List<UsuarioComun> usuarios = new ArrayList<>();
        Connection con = ConnectionBD.getConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(FIND_ALL);
            while (rs.next()) {
                UsuarioComun usuario = new UsuarioComun();
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
     * Verifica si las credenciales (email y contraseña) son correctas para un usuario de tipo Comun.
     *
     * @param email El correo electrónico del usuario.
     * @param password La contraseña del usuario.
     * @return true si las credenciales son válidas, false si no lo son.
     * @throws SQLException Si ocurre un error con la base de datos.
     */
    @Override
    public boolean check(String email, String password) throws SQLException {
        Connection con = ConnectionBD.getConnection();
        try {
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
     * Verifica si un correo electrónico ya está registrado para un usuario de tipo Comun.
     *
     * @param email El correo electrónico a verificar.
     * @return true si el correo existe, false si no.
     * @throws SQLException Si ocurre un error con la base de datos.
     */
    @Override
    public boolean existsByEmail(String email) throws SQLException {
        try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(EXISTS_BY_EMAIL)) {
            pst.setString(1, email.trim());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Actualiza el número de comentarios de un usuario de tipo Comun.
     *
     * @param comun El objeto UsuarioComun cuyo número de comentarios se actualizará.
     * @return true si la actualización fue exitosa, false si no lo fue.
     * @throws SQLException Si ocurre un error con la base de datos.
     */
    public boolean updateNumComentarios(UsuarioComun comun) throws SQLException {
        Boolean update = false;
        Connection con = ConnectionBD.getConnection();
        try (PreparedStatement pst = con.prepareStatement(UPDATE_NUM_COMENTARIOS)) {
            pst.setInt(1, comun.getNum_Comentarios());
            pst.setInt(2, comun.getId_Usuario());
            pst.executeUpdate();
            update = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return update;
    }

    /**
     * Obtiene el número de comentarios de un usuario de tipo Comun.
     *
     * @param usuarioComun El objeto UsuarioComun cuyo número de comentarios se desea obtener.
     * @return El número de comentarios del usuario.
     * @throws SQLException Si ocurre un error con la base de datos.
     */
    public  int obtenerNumeroComentarios(UsuarioComun usuarioComun) throws SQLException {
        Connection con = ConnectionBD.getConnection();
        try (PreparedStatement pst = con.prepareStatement(SELECT_COMENTARIOS)) {
            pst.setInt(1, usuarioComun.getId_Usuario());
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("num_comentarios");
                } else {
                    return 0;
                }
            }
        }
    }

    /**
     * Actualiza el nivel de participación de un usuario de tipo Comun.
     *
     * @param usuarioComun El objeto UsuarioComun cuyo nivel de participación se actualizará.
     * @param participacion El objeto Participacion con el nuevo nivel de participación.
     * @return true si la actualización fue exitosa, false si no lo fue.
     * @throws SQLException Si ocurre un error con la base de datos.
     */
    public boolean updateParticipacion(UsuarioComun usuarioComun, Participacion participacion) throws SQLException {
        boolean updated = false;
        Connection con = ConnectionBD.getConnection();
        try (PreparedStatement pst = con.prepareStatement(UPDATE_PARTICIPACION)) {
            pst.setString(1, participacion.toString());
            pst.setInt(2, usuarioComun.getId_Usuario());
            pst.executeUpdate();
            updated = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return updated;
    }

    /**
     * Obtiene el nivel de participación de un usuario de tipo Comun.
     *
     * @param usuarioComun El objeto UsuarioComun cuyo nivel de participación se desea obtener.
     * @return El nivel de participación del usuario.
     * @throws SQLException Si ocurre un error con la base de datos.
     */
    public String obtenerParticipacion (Usuario usuarioComun) throws SQLException {
        Connection con = ConnectionBD.getConnection();
        try (PreparedStatement pst = con.prepareStatement(SELECT_PARTICIPACION)) {
            pst.setInt(1, usuarioComun.getId_Usuario());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getString("nivel_participacion");
            } else {
                return null;
            }
        }
    }
}
