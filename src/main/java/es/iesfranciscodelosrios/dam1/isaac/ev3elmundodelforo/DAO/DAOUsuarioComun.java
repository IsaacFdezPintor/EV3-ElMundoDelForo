package es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.DAO;

import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.baseDatos.ConnectionBD;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.UsuarioComun;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DAOUsuarioComun {


    private final static String INSERT_USUARIO_COMUN = "INSERT INTO comun (nombre, apellidos, email, password, fecha_registro, tipo_usuario) VALUES (?, ?, ?, ?, ?, ?)";
    private final static String UPDATE_USUARIO_COMUN = "UPDATE comun SET nombre = ?, apellidos = ?, password = ? WHERE email = ?";
    private final static String DELETE_USUARIO_COMUN = "DELETE FROM comun WHERE email = ? AND tipo_usuario = 'COMUN'";
    private final static String FIND_BY_CORREO = "SELECT * FROM comun WHERE email = ? AND tipo_usuario = 'COMUN'";
    private final static String FIND_ALL = "SELECT * FROM comun WHERE tipo_usuario = 'COMUN'";
    private final static String SQL_FIND_BY_EMAIL_BY_PASSWORD = "SELECT * FROM comun WHERE email = ? AND password = ? AND tipo_usuario = 'COMUN'";
    private final static String EXISTS_BY_EMAIL = "SELECT COUNT(*) FROM comun WHERE email = ? AND tipo_usuario = 'COMUN'";


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

    public boolean delete (UsuarioComun usuario) throws SQLException {
        boolean deleted = false;
            if ((usuario!=null)&&findByCorreo(usuario.getEmail())==null){
                try(PreparedStatement pst= ConnectionBD.getConnection().prepareStatement(DELETE_USUARIO_COMUN)){
                pst.setString(1, usuario.getEmail());
                pst.executeUpdate();
                deleted = true;
            } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
        }
        return deleted;
    }
    

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

    

    public boolean verificarCredenciales(String email, String password) throws SQLException {

        try (Connection con = ConnectionBD.getConnection()) {
            PreparedStatement pst = con.prepareStatement(SQL_FIND_BY_EMAIL_BY_PASSWORD);
            pst.setString(1, email);
            pst.setString(2, password);
            try (ResultSet rs = pst.executeQuery()) {
               return rs.next();
            }
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
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

}