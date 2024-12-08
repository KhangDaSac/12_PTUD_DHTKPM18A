package GUI.applications;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        Label statusLabel = new Label("Trạng thái: Đợi xử lý...");
        ProgressIndicator loadingIndicator = new ProgressIndicator();
        loadingIndicator.setVisible(false);

        Button startButton = new Button("Bắt đầu xử lý");
        SomeController controller = new SomeController(statusLabel, loadingIndicator);

        startButton.setOnAction(e -> controller.startLongTask());

        VBox root = new VBox(10, statusLabel, loadingIndicator, startButton);
        Scene scene = new Scene(root, 400, 300);

        primaryStage.setTitle("Ứng dụng JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
