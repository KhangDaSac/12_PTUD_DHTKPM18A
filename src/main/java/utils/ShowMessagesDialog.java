package utils;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;
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
        dialog.setOverlayClose(false);

        closeButton.setOnAction(event -> dialog.close());
        dialogLayout.setActions(closeButton);
        dialog.show();
    }

    public static void showDialog(StackPane root, String title, String content) {
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        dialogLayout.setHeading(new Text(title));
        dialogLayout.setBody(new Text(content));

        JFXDialog dialog = new JFXDialog(root, dialogLayout, JFXDialog.DialogTransition.CENTER);
        dialog.getStyleClass().add("lbMain");
        dialog.setOverlayClose(false);

        dialog.show();
    }

    public static void showDialog(StackPane root, String title, AnchorPane anchorPane, JFXButton closeButton) {
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        dialogLayout.setHeading(new Text(title));
        dialogLayout.setBody(anchorPane);

        JFXDialog dialog = new JFXDialog(root, dialogLayout, JFXDialog.DialogTransition.CENTER);
        dialog.getStyleClass().add("lbMain");
        dialog.setOverlayClose(false);

        closeButton.setOnAction(event -> dialog.close());
        dialog.show();
    }

}
