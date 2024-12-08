package GUI.applications;

import GUI.controllers.DangNhap_GUI_Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Run extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoaderDangNhap = new FXMLLoader(Run.class.getResource("/view/Splash_GUI.fxml"));
        Scene scene = new Scene(fxmlLoaderDangNhap.load());
//        DangNhap_GUI_Controller dangNhapGUIController = fxmlLoaderDangNhap.getController();
//        dangNhapGUIController.setStage(stage);
        stage.setScene(scene);


        stage.setTitle("Ứng dụng bán vé tại ga tàu hỏa");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public static void main(String[] args)
    {
        launch();
    }
}