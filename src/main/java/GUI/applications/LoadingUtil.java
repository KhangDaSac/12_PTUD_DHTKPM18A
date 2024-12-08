package GUI.applications;

import javafx.concurrent.Task;
import javafx.scene.layout.StackPane;

public class LoadingUtil {

    public static <T> void runWithLoading(StackPane loadingIndicator, Task<Void> task) {
        loadingIndicator.setVisible(true); // Hiển thị loading

        task.setOnSucceeded(event -> {
            loadingIndicator.setVisible(false); // Ẩn loading
        });

        task.setOnFailed(event -> {
            loadingIndicator.setVisible(false); // Ẩn loading
        });

        new Thread(task).start();
    }
}
