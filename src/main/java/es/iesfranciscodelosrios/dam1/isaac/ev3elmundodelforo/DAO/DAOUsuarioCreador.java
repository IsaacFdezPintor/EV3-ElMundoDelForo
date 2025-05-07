package es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.DAO;

import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.baseDatos.ConnectionBD;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.UsuarioCreador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOUsuarioCreador {

    private final static String INSERT_USUARIO_CREADOR = "INSERT INTO usuarios (nombre, apellidos, email, password, fecha_registro, tipo_usuario) VALUES (?, ?, ?, ?, ?, ?)";
    private final static String UPDATE_USUARIO_CREADOR = "UPDATE usuarios SET nombre = ?, apellidos = ?, password = ? WHERE email = ?";
    private final static String DELETE_USUARIO_CREADOR = "DELETE FROM usuarios WHERE email = ? AND tipo_usuario = 'CREADOR'";
    private final static String FIND_BY_CORREO = "SELECT * FROM usuarios WHERE email = ? AND tipo_usuario = 'CREADOR'";
    private final static String FIND_ALL = "SELECT * FROM usuarios WHERE tipo_usuario = 'CREADOR'";
    private final static String SQL_FIND_BY_EMAIL_BY_PASSWORD = "SELECT * FROM usuarios WHERE email = ? AND password = ? AND tipo_usuario = 'CREADOR'";

    public UsuarioCreador insert(UsuarioCreador usuario) throws SQLException {
        if ((usuario != null) && findByCorreo(usuario.getEmail()) == null) {
            try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(INSERT_USUARIO_CREADOR)) {
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

    public boolean verificarCredenciales(String email, String password) throws SQLException {
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
}
