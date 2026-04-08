package controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import modelo.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.time.LocalDate;

public class VentanaPrincipalController {


    @FXML private TableView<Producto> tablaProductos;
    @FXML private TableColumn<Producto, Integer> colCodigo;
    @FXML private TableColumn<Producto, String> colNombre;
    @FXML private TableColumn<Producto, Double> colPrecioCosto;
    @FXML private TableColumn<Producto, Double> colPrecioVenta;
    @FXML private TableColumn<Producto, Integer> colStock;

    @FXML private TableColumn<Producto, String> colMarca;
    @FXML private TableColumn<Producto, Object> colFechaVen;
    @FXML private TableColumn<Producto, Object> colReceta;

    @FXML private TextField txtNombre;
    @FXML private TextField txtPrecioCosto;
    @FXML private TextField txtPrecioVenta;
    @FXML private TextField txtStock;

    @FXML private TextField txtMarca;
    @FXML private DatePicker dpVencimiento;
    @FXML private CheckBox chkReceta;

    @FXML private Label lblBienvenida;

    private Inventario inventario;
    private ObservableList<Producto> listaObservable;

    @FXML
    public void initialize() {

        inventario = new Inventario();

        listaObservable = FXCollections.observableArrayList(inventario.getListaProductos());

        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPrecioCosto.setCellValueFactory(new PropertyValueFactory<>("precioCosto"));
        colPrecioCosto.setCellFactory(tc -> new TableCell<>() {
            @Override
            protected void updateItem(Double precio, boolean empty) {
                super.updateItem(precio, empty);
                if (empty || precio == null) {
                    setText(null);
                } else {
                    setText(String.format("S/ %.2f", precio));
                    setStyle("-fx-alignment: CENTER-RIGHT;");
                }
            }
        });
        colPrecioVenta.setCellValueFactory(new PropertyValueFactory<>("precioVenta"));
        colPrecioVenta.setCellFactory(tc -> new TableCell<>() {
            @Override
            protected void updateItem(Double precio, boolean empty) {
                super.updateItem(precio, empty);
                if (empty || precio == null) {
                    setText(null);
                } else {
                    setText(String.format("S/ %.2f", precio));
                    setStyle("-fx-alignment: CENTER-RIGHT;");
                }
            }
        });

        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        colMarca.setCellValueFactory(cellData -> {
            if (cellData.getValue() instanceof Medicamento) {
                return new javafx.beans.property.SimpleStringProperty(((Medicamento) cellData.getValue()).getMarca());
            }
            return new javafx.beans.property.SimpleStringProperty("-"); // Si es Producto general
        });

        colFechaVen.setCellValueFactory(cellData -> {
            if (cellData.getValue() instanceof Medicamento) {
                return new javafx.beans.property.SimpleObjectProperty<>(((Medicamento) cellData.getValue()).getFechaVencimiento());
            }
            return new javafx.beans.property.SimpleObjectProperty<>("N/A");
        });

        colReceta.setCellValueFactory(cellData -> {
            if (cellData.getValue() instanceof Medicamento) {
                return new javafx.beans.property.SimpleObjectProperty<>(((Medicamento) cellData.getValue()).isRequiereReceta());
            }
            return new javafx.beans.property.SimpleObjectProperty<>("N/A");
        });


        tablaProductos.setItems(listaObservable);
    }


    public void establecerUsuario(String nombre) {
        lblBienvenida.setText("¡Bienvenido, " + nombre.toUpperCase() + "!");
    }

    @FXML
    private void Crear() {
        try {
            String nom = txtNombre.getText();
            double pCosto = Double.parseDouble(txtPrecioCosto.getText());
            double pVenta = Double.parseDouble(txtPrecioVenta.getText());
            int sto = Integer.parseInt(txtStock.getText());

            String marca = txtMarca.getText();
            LocalDate fecha = dpVencimiento.getValue();
            boolean receta = chkReceta.isSelected();

            boolean existe = listaObservable.stream().anyMatch(producto -> producto.getNombre().equalsIgnoreCase(nom));
            if (!existe){
                Medicamento nuevo = new Medicamento(nom, pCosto, pVenta, sto, marca, fecha, receta);
                inventario.agregar(nuevo);
                listaObservable.add(nuevo);
                inventario.guardarEnArchivo();
                limpiarCampos();

                String u = Sesion.getInstancia().getUsuarioActual().getNombre();
                System.out.println(u);
            }


        } catch (NumberFormatException e) {
            mostrarAlerta("Error de formato", "Por favor, ingresa números válidos en código, precios y stock.");
        } catch (Exception e) {
            mostrarAlerta("Error", "Ocurrió un problema: " + e.getMessage());
        }
    }

    @FXML
    private void Leer(){
        Producto seleccionado = tablaProductos.getSelectionModel().getSelectedItem();

        int codigo = seleccionado.getCodigo();
        String nombre = seleccionado.getNombre();
        Double pCosto = seleccionado.getPrecioCosto();
        Double pVenta = seleccionado.getPrecioVenta();
        int stock = seleccionado.getStock();

        Medicamento m = (Medicamento) seleccionado;
        String marca = m.getMarca();
        LocalDate fecha = m.getFechaVencimiento();
        boolean receta = m.isRequiereReceta();

        txtNombre.setText(nombre);
        txtPrecioCosto.setText(String.valueOf(pCosto));
        txtPrecioVenta.setText(String.valueOf(pVenta));
        txtStock.setText(String.valueOf(stock));
        txtMarca.setText(marca);
        dpVencimiento.setValue(fecha);
        chkReceta.setSelected(receta);
    }

    @FXML
    private void Eliminar() {
        Producto seleccionado = tablaProductos.getSelectionModel().getSelectedItem();

        if (seleccionado != null) {
            inventario.eliminar(seleccionado.getCodigo());
            listaObservable.remove(seleccionado);
        } else {
            mostrarAlerta("Selección necesaria", "Por favor, selecciona un producto de la tabla para eliminarlo.");
        }
    }

    @FXML
    private void Editar() {
        Producto seleccionado = tablaProductos.getSelectionModel().getSelectedItem();

        if (seleccionado != null) {
            seleccionado.setNombre(txtNombre.getText());
            seleccionado.setPrecioCosto(Double.parseDouble(txtPrecioCosto.getText()));
            seleccionado.setPrecioVenta(Double.parseDouble(txtPrecioVenta.getText()));
            seleccionado.setStock(Integer.parseInt(txtStock.getText()));

            if (seleccionado instanceof Medicamento) {
                Medicamento med = (Medicamento) seleccionado;
                med.setMarca(txtMarca.getText());
                med.setFechaVencimiento(dpVencimiento.getValue());
                med.setRequiereReceta(chkReceta.isSelected());
            }

            tablaProductos.refresh();
            inventario.guardarEnArchivo();      // Guardamos al .dat para que no se pierdan los cambios
            limpiarCampos();           // Vaciamos el formulario

        } else {
            mostrarAlerta("Error", "Debes seleccionar un producto de la tabla primero.");
        }
    }

    private void limpiarCampos() {

        txtNombre.clear();
        txtPrecioCosto.clear();
        txtPrecioVenta.clear();
        txtStock.clear();
        txtMarca.clear();
        dpVencimiento.setValue(null);
        chkReceta.setSelected(false);
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}