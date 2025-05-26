package es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.DAO;

import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.baseDatos.ConnectionBD;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.Foro;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.Texto;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.Usuario;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.UsuarioComun;

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

    /**
     * Inserta un nuevo texto en la base de datos.
     *
     * @param user El usuario que crea el texto.
     * @param foro El foro al que pertenece el texto.
     * @param texto El objeto Texto que contiene la información del mensaje.
     * @return true si el texto se inserta correctamente, false si no.
     * @throws SQLException Si ocurre un error con la base de datos.
     */
    public Boolean insert(Usuario user, Foro foro, Texto texto) throws SQLException {
        boolean inserted = false;
        if (user == null || foro == null || texto == null) {
            throw new IllegalArgumentException("Usuario, foro o texto no pueden estar vacíos.");
        }

        try (
                Connection conn = ConnectionBD.getConnection();
                PreparedStatement pst = conn.prepareStatement(INSERT_TEXT)
        ) {
            pst.setDate(1, texto.getFecha());
            pst.setInt(2, user.getId_Usuario());
            pst.setInt(3, foro.getId_foro());
            pst.setString(4, texto.getTexto());
            pst.executeUpdate();  // Ejecuta la inserción
            inserted = true;
        }
        return inserted;
    }

    /**
     * Recupera todos los textos de un foro dado.
     *
     * @param idForo El ID del foro cuyo texto se desea recuperar.
     * @return Una lista de objetos Texto con todos los textos asociados al foro.
     * @throws SQLException Si ocurre un error con la base de datos.
     */
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

                    // Instanciar y asignar autor
                    Usuario autor = new UsuarioComun();
                    autor.setId_Usuario(rs.getInt("id_usuario"));
                    texto.setAutor(autor);

                    // Instanciar y asignar foro
                    Foro foro = new Foro();
                    foro.setId_foro(rs.getInt("id_foro"));
                    texto.setForo(foro);

                    texto.setTexto(rs.getString("texto"));
                    textos.add(texto);
                }

            }
        }

        return textos;
    }


    /**
     * Elimina un texto de la base de datos.
     *
     * @param texto El objeto Texto que se desea eliminar.
     * @return true si el texto se elimina correctamente, false si no.
     * @throws SQLException Si ocurre un error con la base de datos.
     */
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
