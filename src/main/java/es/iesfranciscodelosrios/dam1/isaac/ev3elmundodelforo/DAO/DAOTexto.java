package es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.DAO;

import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.baseDatos.ConnectionBD;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.Foro;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.Texto;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOTexto {

    private static final String INSERT_TEXT = "INSERT INTO texto (fecha, id_usuario, id_foro, texto) VALUES (?, ?, ?, ?)";
    private static final String FIND_BY_FORO_ID = "SELECT * FROM texto WHERE id_foro = ?";
    private static final String DELETE_TEXTO = "DELETE FROM texto WHERE id_contenido = ?";



    public Boolean insert(Usuario user, Foro foro, Texto texto) throws SQLException {
        boolean inserted = false;
        if (user == null || foro == null || texto == null) {
            throw new IllegalArgumentException("Usuario, foro o texto no pueden estar vacios.");
        }

        try (
                Connection conn = ConnectionBD.getConnection();
                PreparedStatement pst = conn.prepareStatement(INSERT_TEXT)
        ) {
            pst.setDate(1, texto.getFecha());
            pst.setInt(2, user.getId_Usuario());
            pst.setInt(3, foro.getId_foro());
            pst.setString(4, texto.getTexto());
            pst.executeUpdate();
            inserted = true;
        }
        return inserted;
    }


    public List<Texto> findAllByForoId(int idForo) throws SQLException {
        List<Texto> textos = new ArrayList<>();

        try (
                Connection conn = ConnectionBD.getConnection();
                PreparedStatement pst = conn.prepareStatement(FIND_BY_FORO_ID)
        ) {
            pst.setInt(1, idForo);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Texto texto = new Texto();
                    texto.setId_contenido(rs.getInt("id_contenido"));
                    texto.setFecha(rs.getDate("fecha"));
                    texto.setId_usuario(rs.getInt("id_usuario"));
                    texto.setId_foro(rs.getInt("id_foro"));
                    texto.setTexto(rs.getString("texto"));
                    textos.add(texto);
                }
            }
        }

        return textos;
    }

    public boolean delete(Texto texto) throws SQLException {
        boolean deleted = false;
        if (texto == null || texto.getId_contenido() == 0) {
            throw new IllegalArgumentException("Texto inválido o sin ID.");
        }

        try (
                Connection conn = ConnectionBD.getConnection();
                PreparedStatement pst = conn.prepareStatement(DELETE_TEXTO)
        ) {
            pst.setInt(1, texto.getId_contenido());
            int rowsAffected = pst.executeUpdate();
            deleted = rowsAffected > 0;
        }

        return deleted;
    }



}
