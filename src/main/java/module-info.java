module GUI.applications {
    requires javafx.fxml;
    requires javafx.base;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires javafaker;
    requires org.jsoup;
    requires com.jfoenix;
    exports GUI.applications;
    opens GUI.applications to javafx.fxml;
    exports GUI.controllers;
    opens GUI.controllers to javafx.fxml;
    exports DataGeneration;
    exports GUI.controllers.BanVe_GUI_Items;
    opens GUI.controllers.BanVe_GUI_Items to javafx.fxml;
    opens DTO to javafx.base;
}