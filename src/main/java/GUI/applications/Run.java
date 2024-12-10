package GUI.applications;

import GUI.controllers.DangNhap_GUI_Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Run extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoaderDangNhap = new FXMLLoader(Run.class.getResource("/view/Splash_GUI.fxml"));
        Scene scene = new Scene(fxmlLoaderDangNhap.load());

        stage.getIcons().add(new Image(Run.class.getResourceAsStream("/images/app_icon.png")));
        stage.setTitle("Ứng dụng bán vé tàu - Natri Railway Company");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }

    public static void main(String[] args)
    {
        launch();
    }
}