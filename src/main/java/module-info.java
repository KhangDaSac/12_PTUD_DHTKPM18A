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
    exports GUI.controllers.BanVe_GUI_Items;
    opens GUI.controllers.BanVe_GUI_Items to javafx.fxml;
    opens DTO to javafx.base;
    exports GUI.controllers.ThongTinBanVe_GUI_Items;
    opens GUI.controllers.ThongTinBanVe_GUI_Items to javafx.fxml;
    requires javafx.controls;
    requires itextpdf;
    requires org.apache.poi.ooxml;
    requires com.google.zxing;
    requires com.google.zxing.javase;
    requires java.mail;
    exports GUI.controllers.LayVe_GUI_Items to javafx.fxml;
    opens GUI.controllers.LayVe_GUI_Items to javafx.fxml;
    exports GUI.controllers.QuanLyChuyenTau_GUI_Items;
    opens GUI.controllers.QuanLyChuyenTau_GUI_Items to javafx.fxml;
    exports GUI.controllers.KhoiPhucMatKhau_GUI_Items;
    opens GUI.controllers.KhoiPhucMatKhau_GUI_Items to javafx.fxml;
    exports GUI.controllers.DatVe_GUI_Items;
    opens GUI.controllers.DatVe_GUI_Items to javafx.fxml;
    exports GUI.controllers.ThongTinDatVe_GUI_Items;
    opens GUI.controllers.ThongTinDatVe_GUI_Items to javafx.fxml;

    exports utils;
    opens utils to javafx.fxml;

}