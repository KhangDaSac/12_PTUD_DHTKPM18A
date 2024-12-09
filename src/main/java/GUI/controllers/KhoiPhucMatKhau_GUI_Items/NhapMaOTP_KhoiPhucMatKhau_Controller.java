package GUI.controllers.KhoiPhucMatKhau_GUI_Items;

import GUI.controllers.KhoiPhucMatKhau_GUI_Controller;
import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import utils.GmailSender;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NhapMaOTP_KhoiPhucMatKhau_Controller implements Initializable {

    @FXML
    private JFXButton btnQuayLai;

    @FXML
    private JFXButton btnTiepTuc;

    @FXML
    private Label lblThoiGianConLai;

    @FXML
    private Label lblThongBao;

    @FXML
    private Label lblThongBaoGmailGui;

    @FXML
    private TextField txtMaOTP;

    private KhoiPhucMatKhau_GUI_Controller khoiPhucMatKhau_gui_controller;

    public KhoiPhucMatKhau_GUI_Controller getKhoiPhucMatKhau_gui_controller() {
        return khoiPhucMatKhau_gui_controller;
    }

    public void setKhoiPhucMatKhau_gui_controller(KhoiPhucMatKhau_GUI_Controller khoiPhucMatKhau_gui_controller) {
        this.khoiPhucMatKhau_gui_controller = khoiPhucMatKhau_gui_controller;
    }

    private String maOTP;
    private boolean conHieuLuc = true;

    @FXML
    void btnQuayLaiOnAction(ActionEvent event) {
        khoiPhucMatKhau_gui_controller.chuyenTrangNhapNhanVien();
    }

    @FXML
    void btnTiepTucOnAction(ActionEvent event) {
        if(conHieuLuc){
            if(txtMaOTP.getText().equals(maOTP)){
                khoiPhucMatKhau_gui_controller.chuyenTrangNhapMatKhauMoi();
            }else{
                lblThongBao.setText("Mã OTP sai");
            }
        }else{
            System.out.println("Mã OTP đã hết hiệu lực");
        }

    }

    private void taoMaOTP(){
        Random random = new Random();
        maOTP = "";
        for (int i = 0; i < 8; i++) {
            int digit = random.nextInt(10);
            maOTP += digit;
        }

        String mess = "Mã OTP của bạn là " + maOTP + " có hiệu lực trong 120 giây";
        String email = khoiPhucMatKhau_gui_controller.getNhanVien().getEmail();
        String emailAn = "*".repeat(email.length() - 13) + email.substring(email.length() - 13);;

        lblThongBaoGmailGui.setText("Mã OTP đã được gửi đến gmail " + emailAn);

        GmailSender.guiTinNhan(getKhoiPhucMatKhau_gui_controller().getNhanVien().getEmail(), mess);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void khoiTao(){
        taoMaOTP();
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        Runnable task = new Runnable() {
            int countdownSeconds = 120;

            @Override
            public void run() {
                if (countdownSeconds > 0) {
                    Platform.runLater(() -> {
                        lblThoiGianConLai.setText("Mã OTP còn hiệu lực trong " + countdownSeconds + " s");
                    });
                    countdownSeconds--;
                } else {
                    Platform.runLater(() -> {
                        lblThoiGianConLai.setText("Mã OTP đã hết hiệu lực");
                    });
                    conHieuLuc = false;
                    scheduler.shutdown();
                }
            }
        };

        scheduler.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS);

    }

}
