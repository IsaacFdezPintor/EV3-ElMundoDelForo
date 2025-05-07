package es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model;

import java.sql.Date;

public class UsuarioCreador extends Usuario{
    private int Num_ForosCreados;

    public UsuarioCreador() {
        super();
    }

    public UsuarioCreador(String nombre, String apellidos, String email, String password, Date fechaDeRegistro) {
        super(nombre, apellidos, email, password, fechaDeRegistro);
    }

    public int getNum_ForosCreados() {
        return Num_ForosCreados;
    }
    public void setNum_ForosCreados(int num_ForosCreados) {
        Num_ForosCreados = num_ForosCreados;
    }

    @Override
    public String toString() {
        return "Usuariocreador{" +
                "Num_ForosCreados=" + Num_ForosCreados +
                '}';
    }
}
