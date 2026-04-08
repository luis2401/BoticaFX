package controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.*;
import modelo.GestionUsers;
import modelo.Sesion;
import modelo.Usuario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class LoginController {


    private GestionUsers gestionUsuarios;
    private Usuario u;
    private List<Usuario> lista = new ArrayList<>();


    @FXML private TextField txtUsuario;
    @FXML private PasswordField txtPassword;


    @FXML
    public void initialize(){
        gestionUsuarios = new GestionUsers();

        Usuario user1 = new Usuario("alonzo", "vas", "123");
        Usuario user2 = new Usuario("dante", "fue", "123");
        gestionUsuarios.agregar(user1);
        gestionUsuarios.agregar(user2);


    }

    @FXML
    private void clickIniciar() throws IOException {
        String nom = txtUsuario.getText();
        String pass = txtPassword.getText();

        Usuario userE = gestionUsuarios.obtenerUsuario(nom,pass);

        if (userE != null){
            Sesion.getInstancia().setUsuarioActual(userE);
            mostrarAlerta("Inicio de sesión", "Credenciales correctas, bienvenido!");

            try {javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("/ventana-principal.fxml"));
                javafx.scene.Parent root = loader.load();

                javafx.stage.Stage stage = new javafx.stage.Stage();
                stage.setScene(new javafx.scene.Scene(root));
                stage.setTitle("Sistema de Inventario");
                stage.show();

                javafx.stage.Stage loginStage = (javafx.stage.Stage) txtUsuario.getScene().getWindow();
                loginStage.close();


            }catch (java.io.IOException e){
                mostrarAlerta("Error", "No se pudo cargar la ventana principal.");
                e.printStackTrace();
            }

        } else if (nom.isEmpty() && pass.isEmpty()){
            mostrarAlerta("Inicio de sesión", "Introduzca sus credenciales porfavor!");

        }
        else{
            mostrarAlerta("Inicio de sesion", "Credenciales incorrectas!");
        }


    }


    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
