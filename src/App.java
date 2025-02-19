import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    

    public static void main(String[] args) {
        System.out.println("Hello, World!");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
         URL resource = getClass().getResource("./view/Login.fxml");
           Parent root = FXMLLoader.load(resource);
           primaryStage.setScene(new Scene(root));
           primaryStage.show();
           primaryStage.setTitle("Login Form");
    }
}
