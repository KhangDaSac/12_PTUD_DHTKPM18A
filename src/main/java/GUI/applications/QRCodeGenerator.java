package GUI.applications;



import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class QRCodeGenerator {
    public static void generateQRCode(String text, String filePath, int width, int height) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }

    public static void main(String[] args) {
        try {
            String text = "Thông tin vé tàu: SE3, Hà Nội -> Sài Gòn, Ngày: 06/09/2015, Giá: 1.584.000 VNĐ"; // Nội dung QR
            String filePath = "QRCode.png"; // Đường dẫn file lưu QR
            int width = 300; // Chiều rộng
            int height = 300; // Chiều cao

            generateQRCode(text, filePath, width, height);
            System.out.println("QR Code đã được tạo tại: " + filePath);

            // Mở file QR Code sau khi tạo
            File file = new File(filePath);
            if (file.exists()) {
                java.awt.Desktop.getDesktop().open(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
