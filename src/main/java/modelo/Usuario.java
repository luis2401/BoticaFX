package modelo;

public class Usuario {
    private String nombre;
    private String apellido;
    private String contrasena;




    public Usuario(String nombre, String apellido, String contrasena) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasena = contrasena;


    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
