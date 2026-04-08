package modelo;

import modelo.*;

import java.util.ArrayList;
import java.util.List;

public class GestionUsers {

    public List<Usuario> listaUsuario = new ArrayList<>();


    public void agregar(Usuario u){
        listaUsuario.add(u);
    }

    public Usuario obtenerUsuario(String nombreEntrada, String passEntrada) {
        for (Usuario u : listaUsuario) {
            if (u.getNombre().equalsIgnoreCase(nombreEntrada) &&
                    u.getContrasena().equals(passEntrada)) {

                return u;
            }
        }

        return null;
    }




}
