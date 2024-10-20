module GUI.applications {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    exports GUI.applications;
    opens GUI.applications to javafx.fxml;
    exports GUI.controllers;
    opens GUI.controllers to javafx.fxml;
}