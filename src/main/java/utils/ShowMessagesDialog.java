package utils;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.geometry.Pos;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ShowMessagesDialog {
    public static void showDialog(StackPane root, String title, String content, String buttonText) {
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        dialogLayout.setHeading(new Text(title));
        dialogLayout.setBody(new Text(content));

        JFXButton closeButton = new JFXButton(buttonText);
        JFXDialog dialog = new JFXDialog(root, dialogLayout, JFXDialog.DialogTransition.CENTER);
        dialog.getStyleClass().add("lbMain");

        closeButton.setOnAction(event -> dialog.close());
        dialogLayout.setActions(closeButton);
        dialog.show();
    }

    public static boolean showDialogWithLoading(StackPane root, String title, String content) {
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        dialogLayout.setHeading(new Text(title));

        ProgressIndicator loadingIndicator = new ProgressIndicator();
        loadingIndicator.setPrefSize(50, 50);

        VBox vbox = new VBox(loadingIndicator, new Text(content));
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.CENTER);

        dialogLayout.setBody(vbox);


        JFXDialog dialog = new JFXDialog(root, dialogLayout, JFXDialog.DialogTransition.CENTER);
        dialog.getStyleClass().add("lbMain");

        dialog.show();
        return true;
    }

}
