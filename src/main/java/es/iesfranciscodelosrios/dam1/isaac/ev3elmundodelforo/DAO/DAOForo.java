package es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.DAO;

import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.baseDatos.ConnectionBD;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.baseDatos.ConnectionProperties;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.Foro;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.Usuario;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.UsuarioCreador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOForo {

    private final static String INSERT_FORO = "INSERT INTO foro (titulo, descripcion, fecha_creacion, id_creador) VALUES (?, ?, ?, ?)";
    private final static String UPDATE_FORO = "UPDATE foro SET titulo = ?, descripcion = ? WHERE id_foro = ? AND id_creador = ?";
    private final static String DELETE_FORO = "DELETE FROM foro WHERE id_foro = ? AND id_creador = ?";
    private final static String FIND_ALL = "SELECT * FROM foro";
    private final static String FIND_NAME_CREADOR = "SELECT c.id_usuario, c.nombre, c.apellidos, c.email, c.password, c.fecha_registro FROM creador c JOIN foro f ON c.id_usuario = f.id_creador WHERE f.titulo = ?";
    private final static String FIND_FOROS_BY_ID = "SELECT * FROM foro WHERE id_creador = ?";

    /**
     * Inserta un nuevo foro en la base de datos.
     *
     * @param foro    Foro a insertar.
     * @param creador Usuario que crea el foro.
     * @return Foro insertado con su ID generado.
     * @throws SQLException si ocurre un error en la base de datos.
     */
    public Foro insert(Foro foro, Usuario creador) throws SQLException {
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

    /**
     * Actualiza un foro existente si el usuario es su creador.
     *
     * @param foroNuevo Foro con los datos actualizados.
     * @param foroViejo Foro original a actualizar.
     * @param creador   Usuario que realiza la modificación.
     * @return true si la actualización fue exitosa, false en caso contrario.
     * @throws SQLException si ocurre un error en la base de datos.
     */
    public boolean update(Foro foroNuevo, Foro foroViejo, Usuario creador) throws SQLException {
        boolean actualizado = false;
        if (foroNuevo != null && foroViejo != null && creador != null && foroViejo.getId_creador() == creador.getId_Usuario()) {
            try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(UPDATE_FORO)) {
                pst.setString(1, foroNuevo.getTitulo());
                pst.setString(2, foroNuevo.getDescripcion());
                pst.setInt(3, foroViejo.getId_foro());
                pst.setInt(4, creador.getId_Usuario());

                int filasAfectadas = pst.executeUpdate();
                actualizado = filasAfectadas > 0;
            }
        }
        return actualizado;
    }

    /**
     * Elimina un foro si el usuario es su creador.
     *
     * @param foro Foro a eliminar.
     * @return true si se eliminó correctamente, false en caso contrario.
     * @throws SQLException si ocurre un error en la base de datos.
     */
    public boolean delete(Foro foro) throws SQLException {
        boolean eliminado = false;
        if (foro != null) {
            try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(DELETE_FORO)) {
                pst.setInt(1, foro.getId_foro());
                pst.setInt(2, foro.getId_creador());

                eliminado = pst.executeUpdate() > 0;
            }
        }
        return eliminado;
    }


    /**
     * Recupera todos los foros existentes.
     *
     * @return Lista de foros.
     */
    public List<Foro> findAll() {
        List<Foro> foros = new ArrayList<>();
        try (Connection conn = ConnectionBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(FIND_ALL)) {

            while (rs.next()) {
                Foro foro = new Foro(
                        rs.getInt("id_foro"),
                        rs.getString("titulo"),
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

    /**
     * Recupera los foros creados por un usuario específico.
     *
     * @param idCreador ID del creador.
     * @return Lista de foros creados por el usuario.
     */
    public List<Foro> findForosByID(int idCreador) {
        List<Foro> foros = new ArrayList<>();
        try (Connection conn = ConnectionBD.getConnection();
             PreparedStatement pst = conn.prepareStatement(FIND_FOROS_BY_ID)) {

            pst.setInt(1, idCreador);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Foro foro = new Foro(
                            rs.getInt("id_foro"),
                            rs.getString("titulo"),
                            rs.getString("descripcion"),
                            rs.getDate("fecha_creacion"),
                            rs.getInt("id_creador"));
                    foros.add(foro);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foros;
    }

    /**
     * Recupera el usuario creador de un foro dado.
     *
     * @param foro Foro del cual se quiere obtener el creador.
     * @return UsuarioCreador asociado al foro.
     * @throws SQLException si ocurre un error en la base de datos.
     */
    public UsuarioCreador findCreador(Foro foro) throws SQLException {
        UsuarioCreador creador = null;
        try (PreparedStatement pst = ConnectionBD.getConnection().prepareStatement(FIND_NAME_CREADOR)) {
            pst.setString(1, foro.getTitulo());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                creador = new UsuarioCreador();
                creador.setId_Usuario(rs.getInt("id_usuario"));
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
