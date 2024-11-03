package GUI.applications;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TextFieldWithLeadingZeros extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Tạo TextField với giá trị mặc định là "000"
        TextField textField = new TextField("000");

        // Xử lý sự kiện khi người dùng nhập liệu
        textField.setOnKeyTyped(event -> {
            String currentText = textField.getText();

            // Kiểm tra ký tự người dùng nhập có phải là số không
            if (!event.getCharacter().matches("[0-9]")) {
                event.consume(); // Ngăn chặn ký tự không hợp lệ
                return;
            }

            // Nếu độ dài chuỗi hiện tại đã vượt 3, xóa phần "000" và thêm ký tự mới vào cuối
            if (currentText.length() > 3) {
                textField.setText("000" + currentText.substring(3) + event.getCharacter());
            } else {
                textField.setText("000" + event.getCharacter());
            }

            // Đặt con trỏ chuột ở cuối TextField
            textField.positionCaret(textField.getText().length());
            event.consume();
        });

        // Tạo label để mô tả
        Label label = new Label("Nhập số tiền (sau '000'):");

        // Tạo giao diện VBox
        VBox vbox = new VBox(10, label, textField);
        vbox.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        // Thiết lập Scene và Stage
        Scene scene = new Scene(vbox, 300, 150);
        primaryStage.setTitle("TextField with Leading Zeros");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
