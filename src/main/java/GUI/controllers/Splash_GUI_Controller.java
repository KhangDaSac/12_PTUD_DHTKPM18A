package GUI.controllers;

import GUI.applications.Run;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import utils.LoadingTask;

import java.awt.*;

public class Splash_GUI_Controller {

    @FXML
    private AnchorPane anpTienTrinh;

    @FXML
    private Label lblPhanTramLoading;

    public void initialize(){
        LoadingTask task = new LoadingTask();
        task.progressProperty().addListener((observableValue, oldValue, newValue) -> {
            lblPhanTramLoading.setText(String.format("%.0f", newValue.doubleValue() * 100) + "%");
            anpTienTrinh.setPrefWidth(newValue.doubleValue() * 780);
            if(newValue.doubleValue() == 1.0){
                try{
                    Window window = lblPhanTramLoading.getScene().getWindow();
                    Stage stage = (Stage) window;
                    FXMLLoader fxmlLoaderDangNhap = new FXMLLoader(Run.class.getResource("/view/DangNhap_GUI.fxml"));
                    Scene scene = new Scene(fxmlLoaderDangNhap.load());
                    stage.setScene(scene);
                    stage.centerOnScreen();
                }catch (Exception e){

                }

            }
        });

        new Thread(task).start();
    }
}
