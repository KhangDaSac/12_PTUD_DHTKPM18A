package GUI.applications;

import javafx.concurrent.Task;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;

public class SomeController {
    private Label statusLabel;
    private ProgressIndicator loadingIndicator;

    public SomeController(Label statusLabel, ProgressIndicator loadingIndicator) {
        this.statusLabel = statusLabel;
        this.loadingIndicator = loadingIndicator;
    }

    public void startLongTask() {
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {
                // Công việc nặng
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(1000); // Giả lập xử lý
                }
                return null;
            }
        };
    }
}

