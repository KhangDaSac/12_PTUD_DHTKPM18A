module hellloooo.demo {
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
    requires eu.hansolo.toolbox;
    exports GUI.applications;
    opens GUI.applications to javafx.fxml;
    exports GUI.controllers;
    opens GUI.controllers to javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.base;
    requires com.microsoft.sqlserver.jdbc;

    opens DTO to javafx.base;
}