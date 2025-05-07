package es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.test;

import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.Usuario;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.UsuarioComun;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.UsuarioCreador;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 * Clase para probar la implementación de DAOUsuarios
 */
public class TestDAOUsuarios {

    public static void main(String[] args) {
        try {
            // Crear instancia del DAO
            DAOUsuarios daoUsuarios = new DAOUsuarios();
            
            // Probar inserción de un usuario común
            UsuarioComun usuarioComun = new UsuarioComun(
                    "Juan", 
                    "Pérez", 
                    "juan.perez@example.com", 
                    "password123", 
                    Date.valueOf(LocalDate.now())
            );
            
            System.out.println("Insertando usuario común...");
            daoUsuarios.insert(usuarioComun);
            System.out.println("Usuario común insertado con ID: " + usuarioComun.getId_Usuario());
            
            // Probar inserción de un usuario creador
            UsuarioCreador usuarioCreador = new UsuarioCreador(
                    "María", 
                    "López", 
                    "maria.lopez@example.com", 
                    "password456", 
                    Date.valueOf(LocalDate.now())
            );
            usuarioCreador.setNum_ForosCreados(0);
            
            System.out.println("Insertando usuario creador...");
            daoUsuarios.insert(usuarioCreador);
            System.out.println("Usuario creador insertado con ID: " + usuarioCreador.getId_Usuario());
            
            // Probar búsqueda por correo
            System.out.println("Buscando usuario por correo...");
            Usuario usuarioEncontrado = daoUsuarios.findByCorreo("juan.perez@example.com");
            if (usuarioEncontrado != null) {
                System.out.println("Usuario encontrado: " + usuarioEncontrado);
            } else {
                System.out.println("Usuario no encontrado");
            }
            
            // Probar actualización
            if (usuarioEncontrado != null) {
                System.out.println("Actualizando usuario...");
                usuarioEncontrado.setNombre("Juan Carlos");
                daoUsuarios.update(usuarioEncontrado);
                
                // Verificar actualización
                Usuario usuarioActualizado = daoUsuarios.findByCorreo("juan.perez@example.com");
                System.out.println("Usuario actualizado: " + usuarioActualizado);
            }
            
            // Probar verificación de credenciales
            System.out.println("Verificando credenciales...");
            Usuario usuarioVerificado = daoUsuarios.verificarCredenciales("maria.lopez@example.com", "password456");
            if (usuarioVerificado != null) {
                System.out.println("Credenciales correctas para: " + usuarioVerificado.getNombre());
            } else {
                System.out.println("Credenciales incorrectas");
            }
            
            // Probar listado de todos los usuarios
            System.out.println("Listando todos los usuarios...");
            List<Usuario> usuarios = daoUsuarios.findAll();
            for (Usuario u : usuarios) {
                System.out.println(u);
            }
            
            // Probar eliminación
            System.out.println("Eliminando usuarios...");
            boolean eliminadoComun = daoUsuarios.delete("juan.perez@example.com");
            boolean eliminadoCreador = daoUsuarios.delete("maria.lopez@example.com");
            
            System.out.println("Usuario común eliminado: " + eliminadoComun);
            System.out.println("Usuario creador eliminado: " + eliminadoCreador);
            
            // Verificar eliminación
            System.out.println("Verificando eliminación...");
            List<Usuario> usuariosDespuesDeEliminar = daoUsuarios.findAll();
            System.out.println("Número de usuarios después de eliminar: " + usuariosDespuesDeEliminar.size());
            
            // Cerrar conexión
            daoUsuarios.close();
            
        } catch (SQLException e) {
            System.err.println("Error en la prueba: " + e.getMessage());
            e.printStackTrace();
        }
    }
}