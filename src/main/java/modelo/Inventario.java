package modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Inventario {
    private List<Producto> listaProductos;
    private final String NOMBRE_ARCHIVO = "datos_botica.dat";

    public Inventario() {
        this.listaProductos = cargarDesdeArchivo();
        if (!listaProductos.isEmpty()) {

            int ultimoId = listaProductos.get(listaProductos.size() - 1).getCodigo();

            Producto.setContador(ultimoId);
        }
    }

    public boolean checkCodigo(List<Producto> listaProductos, String nombreMed){
        for (int i = 0; i < listaProductos.size() ; i++) {
            if (listaProductos.get(i).getNombre()==nombreMed){
                return true;
            }
        }
        return false;
    }

//     public String nombreMed(List<Producto> listaProductos, int codigo){
//        for (int i = 0; i < listaProductos.size(); i++) {
//            if (listaProductos.get(i).getCodigo() == codigo) {
//                return listaProductos.get(i).getNombre();
//            }
//        }
//        return null;
//    }

    public void agregar(Producto p) {
        listaProductos.add(p);
        guardarEnArchivo();

    }

    public boolean eliminar(int codigo) {
        boolean eliminado = listaProductos.removeIf(p -> p.getCodigo() == codigo);
        if (eliminado) guardarEnArchivo();
        return eliminado;
    }


    public List<Producto> buscarPorNombre(String filtro) {
        return listaProductos.stream()
                .filter(p -> p.getNombre().toLowerCase().contains(filtro.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Medicamento> obtenerVencidos() {
        return listaProductos.stream()
                .filter(p -> p instanceof Medicamento)
                .map(p -> (Medicamento) p)
                .filter(Medicamento::estaVencido)
                .collect(Collectors.toList());
    }


    public List<Producto> obtenerStockBajo() {
        return listaProductos.stream()
                .filter(Producto::tieneStockBajo)
                .collect(Collectors.toList());
    }



    public void guardarEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(NOMBRE_ARCHIVO))) {
            oos.writeObject(listaProductos);
        } catch (IOException e) {
            System.err.println("Error al guardar datos: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private List<Producto> cargarDesdeArchivo() {
        File archivo = new File(NOMBRE_ARCHIVO);
        if (!archivo.exists()) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (List<Producto>) ois.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }


    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }
}