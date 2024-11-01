package GUI.applications;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SearchExample extends Application {

    private ObservableList<String> names = FXCollections.observableArrayList(
            "Alice", "Bob", "Charlie", "David", "Eve", "Frank", "Grace", "Hannah"
    );

    private ListView<String> listView;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Search Example");

        TextField searchField = new TextField();
        searchField.setPromptText("Search...");

        listView = new ListView<>(names);

        // Lắng nghe sự thay đổi của TextField và lọc danh sách
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filterList(newValue);
        });

        VBox vbox = new VBox(searchField, listView);
        Scene scene = new Scene(vbox, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Phương thức để lọc danh sách
    private void filterList(String searchTerm) {
        ObservableList<String> filteredList = FXCollections.observableArrayList();

        for (String name : names) {
            if (name.toLowerCase().contains(searchTerm.toLowerCase())) {
                filteredList.add(name);
            }
        }

        listView.setItems(filteredList);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
