module GUI.applications {
    requires javafx.fxml;
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
    requires javafx.swing;
    exports GUI.applications;
    opens GUI.applications to javafx.fxml;
    exports GUI.controllers;
    opens GUI.controllers to javafx.fxml;

    exports GUI.controllers.DoiVe_GUI_Items;
    opens GUI.controllers.DoiVe_GUI_Items to javafx.fxml;
    opens DTO to javafx.base;

    requires javafx.controls;
    requires itextpdf;
    requires org.apache.poi.ooxml;


    // Other exports and opens statements

}