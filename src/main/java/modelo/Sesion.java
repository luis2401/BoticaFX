package modelo;

public class Sesion {
    private static Sesion instancia;

    private Usuario usuarioActual;

    private Sesion() {}

    public static Sesion getInstancia() {
        if (instancia == null) {
            instancia = new Sesion();
        }
        return instancia;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public void cerrarSesion() {
        this.usuarioActual = null;
    }
}