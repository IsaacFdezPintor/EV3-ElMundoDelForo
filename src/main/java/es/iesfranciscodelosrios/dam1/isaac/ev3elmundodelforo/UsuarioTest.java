package es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo;

import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.DAO.DAOForo;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.DAO.DAOUsuarioComun;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.DAO.DAOUsuarioCreador;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.Foro;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.UsuarioComun;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.UsuarioCreador;

import java.sql.Date;
import java.sql.SQLException;

public class UsuarioTest {
    public static void main(String[] args) throws SQLException {
       // UsuarioCreador usuarioCreador = new UsuarioCreador("Isaac", "Gonzalez", "isaac@gmail.com", "1234");
       // DAOUsuarioCreador usuarioCreadorDAO = new DAOUsuarioCreador();
        //UsuarioComun usuarioComun = new UsuarioComun("Luis", "Gonzalez", "luis@gmail.com", "1234", Date.valueOf("2023-10-01"));
     //   DAOUsuarioComun usuarioComunDAO = new DAOUsuarioComun();
       // usuarioComunDAO.insert(usuarioComun);
      //  System.out.println(usuarioComunDAO.check(usuarioComun.getEmail(), usuarioComun.getPassword()));
        //DAOForo foroDAO = new DAOForo();
       //usuarioCreadorDAO.insert(usuarioCreador);
        /*Foro foro = new Foro("Titulo", "Descripcion", Date.valueOf("2023-10-01"),3);
        foroDAO.insert(foro, usuarioCreador);
        System.out.println(usuarioCreadorDAO.check(usuarioCreador.getEmail(),usuarioCreador.getPassword()));

        System.out.println(foroDAO.findCreador(foro).getNombre());*/

        /**Foro foro = new Foro();
        foro.setTitulo("Foro de prueba");
        foro.setDescripcion("Descripcion de prueba");
        foro.setFecha_creacion(Date.valueOf("2023-10-01"));
        DAOForo foroDAO = new DAOForo();
        DAOUsuarioCreador usuarioCreadorDAO = new DAOUsuarioCreador();
        DAOUsuarioComun usuarioComunDAO = new DAOUsuarioComun();
        foroDAO.insert(foro, usuarioCreador);
*/

        /*UsuarioCreador admin = new UsuarioCreador("Admin", "Lucas", "usuariocreador@gmail.com", "1234");
        DAOUsuarioCreador adminDAO = new DAOUsuarioCreador();
        adminDAO.insert(admin);
        System.out.println(adminDAO.check(admin.getEmail(), admin.getPassword()));
        System.out.println(admin.getTipoUsuario());
        /*UsuarioComun comun = new UsuarioComun("Comun", "Luis", "comun@gmail.com", "1234");
        DAOUsuarioComun comunDAO = new DAOUsuarioComun();
        comunDAO.insert(comun);
        System.out.println(comunDAO.check(comun.getEmail(), comun.getPassword()));*/

        /*Foro foroBaseDatos = new Foro();
        foroBaseDatos.setTitulo("Foro de Base de Datos");
        foroBaseDatos.setDescripcion("XAMPP ES UNA MIERDA");
        foroBaseDatos.setId_creador(3);
        DAOForo daoForo = new DAOForo();
        daoForo.insert(foroBaseDatos, admin);/* No funciona pq hay q estar logeado*/


    }

}
