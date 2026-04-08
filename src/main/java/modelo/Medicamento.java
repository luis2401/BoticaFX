package modelo;

import java.time.LocalDate;

public class Medicamento extends Producto{
    private String marca;
    private LocalDate fechaVencimiento;
    private boolean requiereReceta;

    public Medicamento(String nombre, double precioCosto, double precioVenta, int stock, String marca, LocalDate fechaVencimiento, boolean requiereReceta) {
        super(nombre, precioCosto, precioVenta, stock);
        this.marca = marca;
        this.fechaVencimiento = fechaVencimiento;
        this.requiereReceta = requiereReceta;
    }

    @Override
    public String toString() {
        return super.toString() + "Medicamento{" +
                "marca='" + marca + '\'' +
                ", fechaVencimiento=" + fechaVencimiento +
                ", requiereReceta=" + requiereReceta +
                '}';
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public boolean isRequiereReceta() {
        return requiereReceta;
    }

    public void setRequiereReceta(boolean requiereReceta) {
        this.requiereReceta = requiereReceta;
    }

    public boolean estaVencido() {
        return LocalDate.now().isAfter(fechaVencimiento);
    }

    @Override
    public boolean tieneStockBajo() {
        return this.getStock() <= 10;
    }

    public boolean estaProximoAVencer() {
        LocalDate hoy = LocalDate.now();
        return fechaVencimiento.isBefore(hoy.plusMonths(1)) && !estaVencido();
    }
}