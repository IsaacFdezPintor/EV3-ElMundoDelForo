package es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.DAO;

import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.baseDatos.ConnectionBD;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.Foro;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.UsuarioCreador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOForo {

    private final static String INSERT_FORO = "INSERT INTO foros (titulo, descripcion, fecha_creacion, id_creador) VALUES (?, ?, ?, ?)";
    private final static String UPDATE_FORO = "UPDATE foros SET titulo = ?, descripcion = ? WHERE id_foro = ? AND id_creador = ?";
    private final static String DELETE_FORO = "DELETE FROM foros WHERE id_foro = ? AND id_creador = ?";
    private final static String FIND_BY_ID = "SELECT * FROM foros WHERE id_foro = ?";
    private final static String FIND_ALL = "SELECT * FROM foros";

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

    public List<Foro> findAll() throws SQLException {
        List<Foro> foros = new ArrayList<>();
        try (Statement st = ConnectionBD.getConnection().createStatement()) {
            ResultSet rs = st.executeQuery(FIND_ALL);
            while (rs.next()) {
                Foro foro = new Foro();
                foro.setId_foro(rs.getInt("id_foro"));
                foro.setTitulo(rs.getString("titulo"));
                foro.setDescripcion(rs.getString("descripcion"));
                foro.setFecha_creacion(rs.getDate("fecha_creacion"));
                foro.setId_creador(rs.getInt("id_creador"));
                foros.add(foro);
            }
        }
        return foros;
    }
}

