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
        FXMLLoader fxmlLoaderDangNhap = new FXMLLoader(Run.class.getResource("/view/KhungGiaoDien.fxml"));
        Scene sceneDangNhap = new Scene(fxmlLoaderDangNhap.load());
//        DangNhap_GUI_Controller dangNhapGUIController = fxmlLoaderDangNhap.getController();
//        dangNhapGUIController.setStage(stage);

<<<<<<< HEAD
        stage.getIcons().add(new Image(Run.class.getResourceAsStream("/images/app_icon.png")));
        stage.setTitle("Ứng dụng bán vé tàu - Natri Railway Company");
=======
        stage.setTitle("Ứng dụng bán vé tại ga tàu hỏa");
>>>>>>> ba8d3caccc6e91114eda0c306f97c9e16adf148a
        stage.setResizable(false);
        stage.setScene(sceneDangNhap);
        stage.show();

    }

    public static void main(String[] args)
    {
        launch();
    }
}