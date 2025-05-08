package es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo;

import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.DAO.DAOUsuarioCreador;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.UsuarioCreador;

import java.sql.Date;
import java.sql.SQLException;

public class UsuarioTest {
    public static void main(String[] args) throws SQLException {
        UsuarioCreador usuarioCreador = new UsuarioCreador("Isaac", "Gonzalez", "isaac@gmail.com", "1234", Date.valueOf("2023-10-01"));
        DAOUsuarioCreador usuarioCreadorDAO = new DAOUsuarioCreador();
       usuarioCreadorDAO.insert(usuarioCreador);
    }

}
