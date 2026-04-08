import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // IMPORTANTE: La ruta debe coincidir con donde está tu archivo .fxml
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/login.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Botica Sinchi Roca - Inicio de sesion");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}