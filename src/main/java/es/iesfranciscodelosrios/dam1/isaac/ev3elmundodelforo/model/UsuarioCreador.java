package es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model;

import java.sql.Date;

public class UsuarioCreador extends Usuario{
    private int Num_ForosCreados;

    public UsuarioCreador() {
        super();
    }

    public UsuarioCreador(String nombre, String apellidos, String email, String password) {
        super(nombre, apellidos, email, password);
    }

    public int getNum_ForosCreados() {
        return Num_ForosCreados;
    }
    public void setNum_ForosCreados() {
        Num_ForosCreados = Num_ForosCreados + 1;
    }

    @Override
    public String toString() {
        return "Usuariocreador{" +
                "Num_ForosCreados=" + Num_ForosCreados +
                '}';
    }
}
