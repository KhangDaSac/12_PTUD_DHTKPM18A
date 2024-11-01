package GUI.applications;

import com.jfoenix.controls.JFXAutoCompletePopup;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AutoCompleteTextFieldExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        JFXTextField textField = new JFXTextField();
        textField.setPromptText("Nhập để tìm kiếm...");

        JFXAutoCompletePopup<String> autoCompletePopup = new JFXAutoCompletePopup<>();
        autoCompletePopup.getSuggestions().addAll(
                "Hà Nội", "TP. Hồ Chí Minh", "Đà Nẵng", "Huế", "Hải Phòng"
        );

        // Cập nhật gợi ý khi người dùng nhập
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                autoCompletePopup.filter(item -> item.toLowerCase().contains(newValue.toLowerCase()));
                if (!autoCompletePopup.getFilteredSuggestions().isEmpty()) {
                    autoCompletePopup.show(textField);
                } else {
                    autoCompletePopup.hide();
                }
            } else {
                autoCompletePopup.hide();
            }
        });

        VBox vbox = new VBox(textField);
        Scene scene = new Scene(vbox, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("AutoComplete TextField Example");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
