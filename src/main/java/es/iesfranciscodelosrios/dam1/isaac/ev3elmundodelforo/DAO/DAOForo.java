package es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.DAO;

import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.baseDatos.ConnectionBD;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.baseDatos.ConnectionProperties;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.Foro;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.UsuarioCreador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOForo {

    private final static String INSERT_FORO = "INSERT INTO foro (titulo, descripcion, fecha_creacion, id_creador) VALUES (?, ?, ?, ?)";
    private final static String UPDATE_FORO = "UPDATE foro SET titulo = ?, descripcion = ? WHERE id_foro = ? AND id_creador = ?";
    private final static String DELETE_FORO = "DELETE FROM foro WHERE id_foro = ? AND id_creador = ?";
    private final static String FIND_BY_ID = "SELECT * FROM foro WHERE id_foro = ?";
    private final static String FIND_ALL = "SELECT * FROM foro";
    private final static String FIND_NAME_CREADOR =
            "SELECT c.id_usuario, c.nombre, c.apellidos, c.email, c.password, c.fecha_registro " +
                    "FROM creador c " +
                    "JOIN foro f ON c.id_usuario = f.id_creador " +
                    "WHERE f.titulo = ?";
    ;

    public Foro insert(Foro foro, UsuarioCreador creador) throws SQLException {
        if (foro != null && creador != null) {
            try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(INSERT_FORO, Statement.RETURN_GENERATED_KEYS)) {
                pst.setString(1, foro.getTitulo());
                pst.setString(2, foro.getDescripcion());
                pst.setDate(3, foro.getFecha_creacion());
                pst.setInt(4, creador.getId_Usuario());

                pst.executeUpdate();
                ResultSet rs = pst.getGeneratedKeys();
                if (rs.next()) {
                    foro.setId_foro(rs.getInt(1));
                    foro.setId_creador(creador.getId_Usuario());
                }
            }
        }
        return foro;
    }

    public boolean update(Foro foro, UsuarioCreador creador) throws SQLException {
        boolean actualizado = false;
        if (foro != null && creador != null && foro.getId_creador() == creador.getId_Usuario()) {
            try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(UPDATE_FORO)) {
                pst.setString(1, foro.getTitulo());
                pst.setString(2, foro.getDescripcion());
                pst.setInt(3, foro.getId_foro());
                pst.setInt(4, creador.getId_Usuario());

                actualizado = pst.executeUpdate() > 0;
            }
        }
        return actualizado;
    }

    public boolean delete(int idForo, UsuarioCreador creador) throws SQLException {
        boolean eliminado = false;
        if (creador != null) {
            try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(DELETE_FORO)) {
                pst.setInt(1, idForo);
                pst.setInt(2, creador.getId_Usuario());

                eliminado = pst.executeUpdate() > 0;
            }
        }
        return eliminado;
    }

    public Foro findById(int id) throws SQLException {
        Foro foro = null;
        try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(FIND_BY_ID)) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                foro = new Foro();
                foro.setId_foro(rs.getInt("id_foro"));
                foro.setTitulo(rs.getString("titulo"));
                foro.setDescripcion(rs.getString("descripcion"));
                foro.setFecha_creacion(rs.getDate("fecha_creacion"));
                foro.setId_creador(rs.getInt("id_creador"));
            }
        }
        return foro;
    }

    public List<Foro> findAll() {
        List<Foro> foros = new ArrayList<>();

        try (Connection conn = ConnectionBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(FIND_ALL)) {

            while (rs.next()) {
                Foro foro = new Foro(rs.getString("titulo"),
                        rs.getString("descripcion"),
                        rs.getDate("fecha_creacion"),
                        rs.getInt("id_creador"));
                foros.add(foro);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return foros;
    }


    public UsuarioCreador findCreador(Foro foro) throws SQLException {
        UsuarioCreador creador = null;
            try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(FIND_NAME_CREADOR)) {
                pst.setString(1, foro.getTitulo());
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    creador = new UsuarioCreador();
                    creador.setId_Usuario(rs.getInt("id_usuario"));
                    System.out.println("ID Creador: " + creador.getId_Usuario());
                    creador.setNombre(rs.getString("nombre"));
                    creador.setApellidos(rs.getString("apellidos"));
                    creador.setEmail(rs.getString("email"));
                    creador.setPassword(rs.getString("password"));
                    creador.setFechaDeRegistro(rs.getDate("fecha_registro"));
                }
            }
        return creador;
    }
}

