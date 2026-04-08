package modelo;

import java.io.Serializable;
import java.time.LocalDate;

public class Producto implements Serializable {
    private final int codigo;
    private String nombre;
    private double precioCosto;
    private double precioVenta;
    private int stock;
    private static int contador;

    public Producto(String nombre, double precioCosto, double precioVenta, int stock) {
        this.nombre = nombre;
        this.precioCosto = precioCosto;
        this.precioVenta = precioVenta;
        this.stock = stock;
        contador++;
        this.codigo=contador;
    }

    public static void setContador(int nuevoValor) {
        contador = nuevoValor;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", precioCosto=" + precioCosto +
                ", precioVenta=" + precioVenta +
                ", stock=" + stock +
                '}';
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecioCosto() {
        return precioCosto;
    }

    public void setPrecioCosto(double precioCosto) {
        this.precioCosto = precioCosto;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean tieneStockBajo() {
        return this.stock <= 5;
    }

    public double calcularMargenGanancia() {
        return this.precioVenta - this.precioCosto;
    }
}

