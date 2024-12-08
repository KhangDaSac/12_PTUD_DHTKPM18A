package GUI.applications;

import GUI.controllers.DangNhap_GUI_Controller;
import GUI.controllers.DoiVe_GUI_Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Run extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoaderDangNhap = new FXMLLoader(Run.class.getResource("/view/DoiVe_GUI.fxml"));
        Scene sceneDangNhap = new Scene(fxmlLoaderDangNhap.load());
//        DangNhap_GUI_Controller dangNhapGUIController = fxmlLoaderDangNhap.getController();
//        dangNhapGUIController.setStage(stage);

        stage.setTitle("Ứng dụng bán vé tại ga tàu hỏa");
        stage.setResizable(false);
        stage.setScene(sceneDangNhap);
        stage.show();

    }

    public static void main(String[] args)
    {
        launch();
    }
}