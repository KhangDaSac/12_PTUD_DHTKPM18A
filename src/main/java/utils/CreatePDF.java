package utils;

import DTO.*;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CreatePDF {
    public static void taoHoaDonBanVe(HoaDonBanVe hoaDonBanVe) {
        String filePath = "documents/HoaDonBanVe/" + hoaDonBanVe.getMaHoaDonBanVe() + ".pdf";
        String logoPath = "src/main/resources/images/HoaDon/Logo.png";
        String qrCodePath = "documents/HoaDonBanVe/QRCode/QRCode_" + hoaDonBanVe.getMaHoaDonBanVe() + ".png";
        try {

            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();


            String fontPath = "C:\\Windows\\Fonts\\arial.ttf";
            BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            //font thường
            Font font = new Font(baseFont, 12);
            //font in đậm
            Font boldFont = new Font(baseFont, 12, Font.BOLD);

            //font tieu de
            Font titleFont = new Font(baseFont, 20, Font.BOLD);


            Image logo = Image.getInstance(logoPath);
            logo.scaleToFit(100, 50);
            logo.setAlignment(Image.ALIGN_CENTER);
            document.add(logo);


            document.add(Chunk.NEWLINE);


            Paragraph title = new Paragraph("HÓA ĐƠN BÁN VÉ", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Mã hóa đơn
            CreateQRCode.generateQRCode(hoaDonBanVe.getMaHoaDonBanVe(), qrCodePath, 80, 80);
            Image qrCode = Image.getInstance(qrCodePath);
            qrCode.setAlignment(Element.ALIGN_LEFT);
            document.add(qrCode);

            Paragraph maHoaDonPar = new Paragraph();
            maHoaDonPar.setAlignment(Element.ALIGN_LEFT);
            maHoaDonPar.add(new Chunk("Mã hóa đơn: ", boldFont));
            maHoaDonPar.add(new Chunk(hoaDonBanVe.getMaHoaDonBanVe(), font));
            document.add(maHoaDonPar);

            document.add(Chunk.NEWLINE);


            // Thông tin công ty
            Paragraph companyInfo = new Paragraph();
            companyInfo.add(new Chunk("Công ty: ", boldFont));
            companyInfo.add(new Chunk("Công ty đường sắt Natri - Natri railway company", font));
            document.add(companyInfo);

            Paragraph companyAddress = new Paragraph();
            companyAddress.add(new Chunk("Địa chỉ: ", boldFont));
            companyAddress.add(new Chunk("Nguyễn Văn Bảo, phường 4, thành phố Hồ Chí Minh", font));
            document.add(companyAddress);

            Paragraph companyInfo2 = new Paragraph();
            companyInfo2.add(new Chunk("Điện thoại: ", boldFont));
            companyInfo2.add(new Chunk("(+84) 375 684 002", font));
            document.add(companyInfo2);

            document.add(Chunk.NEWLINE);

            Paragraph customerInfo = new Paragraph();
            customerInfo.add(new Chunk("Tên khách hàng: ", boldFont));
            customerInfo.add(new Chunk(hoaDonBanVe.getKhachHangMuaVe().getTenKhachHang(), font));
            document.add(customerInfo);

            Paragraph customerInfo2 = new Paragraph();
            customerInfo2.add(new Chunk("CCCD: ", boldFont));
            customerInfo2.add(new Chunk(hoaDonBanVe.getKhachHangMuaVe().getCCCD(), font));
            document.add(customerInfo2);

            Paragraph customerInfo3 = new Paragraph();
            customerInfo3.add(new Chunk("Số điện thoại: ", boldFont));
            customerInfo3.add(new Chunk(hoaDonBanVe.getKhachHangMuaVe().getSoDienThoai(), font));
            document.add(customerInfo3);


            Paragraph date = new Paragraph();
            date.add(new Chunk("Thời gian lập hóa đơn: ", boldFont));
            date.add(new Chunk(TimeFormat.formatLocalDateTime(hoaDonBanVe.getThoiGianLap()), font));
            document.add(date);

            document.add(Chunk.NEWLINE);


            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);

            float[] columnWidths = {1f, 5f, 6f, 4f};
            table.setWidths(columnWidths);

            String[] headers = {"STT", "Mã vé", "Tên vé", "Tiền vé"};
            for (String header : headers) {
                PdfPCell headerCell = new PdfPCell(new Phrase(header, boldFont));
                headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                headerCell.setPadding(5);
                table.addCell(headerCell);
            }

            int i = 1;

            for (Ve ve : hoaDonBanVe.getDanhSachVe()) {

                PdfPCell cell1 = new PdfPCell(new Phrase(String.valueOf(i), font));
                cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell1.setPadding(5);
                table.addCell(cell1);

                PdfPCell cell2 = new PdfPCell(new Phrase(ve.getMaVe(), font));
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell2.setPadding(5);
                table.addCell(cell2);

                String tenVe = "Chuyến tàu " + ve.getThongTinGaTauDi().getChuyenTau().getMaChuyenTau().substring(0, 4)
                        + " – " + TimeFormat.formatLocalDateTime(ve.getThongTinGaTauDi().getThoiGianDi())
                        + " – " + ve.getThongTinGaTauDi().getGaTau().getTenGaTau()
                        + " – " + ve.getThongTinGaTauDen().getGaTau().getTenGaTau()
                        + " – " + (ve.getLoaiVe().equals(LoaiVe.VECANHAN) ? "Vé cá nhân" : "Vé tập thể");

                PdfPCell cell3 = new PdfPCell(new Phrase(tenVe, font));
                cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell3.setPadding(5);
                table.addCell(cell3);

                PdfPCell cell4 = new PdfPCell(new Phrase(CurrencyFormat.currencyFormat(ve.tienVeCuoi()), font));
                cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell4.setPadding(5);
                table.addCell(cell4);

                i++;
            }

            PdfPCell totalLabelCell = new PdfPCell(new Phrase("TỔNG TIỀN", boldFont));
            totalLabelCell.setColspan(3);
            totalLabelCell.setPadding(5);
            totalLabelCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(totalLabelCell);

            PdfPCell totalValueCell = new PdfPCell(new Phrase(CurrencyFormat.currencyFormat(hoaDonBanVe.tongTienCuoi()), boldFont));
            totalValueCell.setPadding(5);
            totalValueCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(totalValueCell);

            document.add(table);


            document.add(new Paragraph("Ghi chú:", boldFont));
            document.add(new Paragraph(" – Hóa đơn này được tạo tự động.", font));
            document.add(new Paragraph(" – Tiền vé đã bao gồm thuế giá trị gia tăng 8%.", font));
            document.add(new Paragraph(" – Hóa đơn không phải là vé và không có giá trị lên tàu.", font));


            document.close();
            System.out.println("Hóa đơn đã được tạo tại: " + filePath);


            File wordFile = new File(filePath);
            if (!wordFile.exists()) {
                return;
            }
            Desktop desktop = Desktop.getDesktop();

            if (desktop.isSupported(Desktop.Action.OPEN)) {
                desktop.open(wordFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void taoVe(Ve ve) {
        String filePath = "documents/Ve/" + ve.getMaVe() + ".pdf";
        String qrCodePath = "documents/Ve/QRCode/QRCode_" + ve.getMaVe() + ".png";
        try {
            CreateQRCode.generateQRCode(ve.getMaVe(), qrCodePath, 150, 150);
            Rectangle pageSize = new Rectangle(300, 120 * ve.getDanhSachChiTietVe().size() + 500);
            Document document = new Document(pageSize);
            document.setMargins(20, 20, 20, 20);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));

            document.open();

            String fontPath = "C:\\Windows\\Fonts\\arial.ttf";
            BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

            Font font = new Font(baseFont, 12);
            Font font18 = new Font(baseFont, 18);
            Font fontChuThich = new Font(baseFont, 12, Font.ITALIC);
            Font boldFont = new Font(baseFont, 12, Font.BOLD);
            Font boldFont16 = new Font(baseFont, 16, Font.BOLD);

            // Logo công ty (nếu có)
            Paragraph company = new Paragraph("TỔNG CÔNG TY ĐƯỜNG SẮT NATRI", boldFont);
            company.setAlignment(Element.ALIGN_CENTER);
            document.add(company);

            Paragraph title = new Paragraph("VÉ TÀU HỎA", new Font(baseFont, 16, Font.BOLD));
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            Paragraph titleEnglish = new Paragraph("TRAIN TICKET", boldFont);
            titleEnglish.setAlignment(Element.ALIGN_CENTER);
            document.add(titleEnglish);


            Image qrCode = Image.getInstance(qrCodePath);
            qrCode.setAlignment(Element.ALIGN_CENTER);
            document.add(qrCode);

            Paragraph loaiVe = new Paragraph();
            loaiVe.setAlignment(Element.ALIGN_CENTER);
            loaiVe.add(new Chunk(ve.getLoaiVe().equals(LoaiVe.VECANHAN) ? "VÉ CÁ NHÂN" : "VÉ TẬP THỂ", boldFont16));
            document.add(loaiVe);
            document.add(Chunk.NEWLINE);

            Paragraph maVe = new Paragraph();
            maVe.setAlignment(Element.ALIGN_CENTER);
            maVe.add(new Chunk("Mã vé: ", boldFont));
            maVe.add(new Chunk(ve.getMaVe(), font));
            document.add(maVe);

            PdfPTable tableGaTau = new PdfPTable(2);
            tableGaTau.setWidthPercentage(100);
            tableGaTau.setSpacingBefore(10);

            String[] headers = {"Ga đi", "Ga đến"};
            for (String header : headers) {
                PdfPCell headerCell = new PdfPCell(new Phrase(header, boldFont));
                headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                headerCell.setBorder(Rectangle.NO_BORDER);
                headerCell.setPadding(5);
                tableGaTau.addCell(headerCell);
            }

            PdfPCell cell1 = new PdfPCell(new Phrase(ve.getThongTinGaTauDi().getGaTau().getTenGaTau(), font18));
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setBorder(Rectangle.NO_BORDER);
            tableGaTau.addCell(cell1);

            PdfPCell cell2 = new PdfPCell(new Phrase(ve.getThongTinGaTauDen().getGaTau().getTenGaTau(), font18));
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setBorder(Rectangle.NO_BORDER);
            tableGaTau.addCell(cell2);
            document.add(tableGaTau);
            document.add(Chunk.NEWLINE);

            PdfPTable tableThongTinChuyenTau = new PdfPTable(2);
            tableThongTinChuyenTau.setWidthPercentage(100);
            tableThongTinChuyenTau.setSpacingBefore(10);

            addRowToTable(tableThongTinChuyenTau, "Tàu", ve.getThongTinGaTauDi().getChuyenTau().getMaChuyenTau().substring(0, 4), boldFont, font);
            addRowToTable(tableThongTinChuyenTau, "Thời gian đi", TimeFormat.formatLocalDateTime(ve.getThongTinGaTauDi().getThoiGianDi()), boldFont, font);
            document.add(tableThongTinChuyenTau);

            for (ChiTietVe chiTietVe : ve.getDanhSachChiTietVe()) {

                PdfPTable table = new PdfPTable(2);
                table.setWidthPercentage(100);
                table.setSpacingBefore(10);

                addRowToTable(table, "Toa", String.valueOf(chiTietVe.getCho().getToaTau().getThuTuToa()), boldFont, font);
                addRowToTable(table, "Chỗ", String.valueOf(chiTietVe.getCho().getSoCho()), boldFont, font);
                addRowToTable(table, "Loại toa", chiTietVe.getCho().getToaTau().getLoaiToaTau().getTenLoaiToa(), boldFont, font);
                addRowToTable(table, "Loại chỗ", chiTietVe.getCho().getLoaiCho().getTenLoaiCho(), boldFont, font);
                addRowToTable(table, "Họ tên", chiTietVe.getKhachHang().getTenKhachHang(), boldFont, font);
                addRowToTable(table, "CCCD", chiTietVe.getKhachHang().getCCCD(), boldFont, font);
                addRowToTable(table, "Giá chỗ", CurrencyFormat.currencyFormat(chiTietVe.getGiaCho()), boldFont, font);

                //document.add(Chunk.NEWLINE);
                document.add(table);

            }


            // Ghi chú
            Paragraph note = new Paragraph("Vé tàu hỏa không phải là hóa đơn.", fontChuThich);
            note.setAlignment(Element.ALIGN_CENTER);
            note.setSpacingBefore(10);
            document.add(note);

            document.close();
            System.out.println("Vé tàu đã được tạo tại: " + filePath);

            // Mở tệp PDF
            File file = new File(filePath);
            if (file.exists()) {
                Desktop.getDesktop().open(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private static void addRowToTable(PdfPTable table, String label, String value, Font labelFont, Font valueFont) {
        PdfPCell labelCell = new PdfPCell(new Phrase(label, labelFont));
        labelCell.setBorder(Rectangle.NO_BORDER);
        table.addCell(labelCell);

        PdfPCell valueCell = new PdfPCell(new Phrase(value, valueFont));
        valueCell.setBorder(Rectangle.NO_BORDER);
        table.addCell(valueCell);
    }

    public static void taoHoaDonLayVe(HoaDonLayVe hoaDonLayVe) {
        String filePath = "documents/HoaDonLayVe/" + hoaDonLayVe.getMaHoaDonLayVe() + ".pdf";
        String logoPath = "src/main/resources/images/HoaDon/Logo.png";
        String qrCodePath = "documents/HoaDonLayVe/QRCode/QRCode_" + hoaDonLayVe.getMaHoaDonLayVe() + ".png";
        try {

            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();


            String fontPath = "C:\\Windows\\Fonts\\arial.ttf";
            BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            //font thường
            Font font = new Font(baseFont, 12);
            //font in đậm
            Font boldFont = new Font(baseFont, 12, Font.BOLD);

            //font tieu de
            Font titleFont = new Font(baseFont, 20, Font.BOLD);


            Image logo = Image.getInstance(logoPath);
            logo.scaleToFit(100, 50);
            logo.setAlignment(Image.ALIGN_CENTER);
            document.add(logo);


            document.add(Chunk.NEWLINE);

            Paragraph title = new Paragraph("HÓA ĐƠN LẤY VÉ", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Mã hóa đơn
            CreateQRCode.generateQRCode(hoaDonLayVe.getMaHoaDonLayVe(), qrCodePath, 80, 80);
            Image qrCode = Image.getInstance(qrCodePath);
            qrCode.setAlignment(Element.ALIGN_LEFT);
            document.add(qrCode);

            Paragraph maHoaDonPar = new Paragraph();
            maHoaDonPar.setAlignment(Element.ALIGN_LEFT);
            maHoaDonPar.add(new Chunk("Mã hóa đơn: ", boldFont));
            maHoaDonPar.add(new Chunk(hoaDonLayVe.getMaHoaDonLayVe(), font));
            document.add(maHoaDonPar);

            document.add(Chunk.NEWLINE);

            // Thông tin công ty
            Paragraph companyInfo = new Paragraph();
            companyInfo.add(new Chunk("Công ty: ", boldFont));
            companyInfo.add(new Chunk("Công ty đường sắt Natri - Natri railway company", font));
            document.add(companyInfo);

            Paragraph companyAddress = new Paragraph();
            companyAddress.add(new Chunk("Địa chỉ: ", boldFont));
            companyAddress.add(new Chunk("Nguyễn Văn Bảo, phường 4, thành phố Hồ Chí Minh", font));
            document.add(companyAddress);

            Paragraph companyInfo2 = new Paragraph();
            companyInfo2.add(new Chunk("Điện thoại: ", boldFont));
            companyInfo2.add(new Chunk("(+84) 375 684 002", font));
            document.add(companyInfo2);

            document.add(Chunk.NEWLINE);

            Paragraph customerInfo = new Paragraph();
            customerInfo.add(new Chunk("Tên khách hàng: ", boldFont));
            customerInfo.add(new Chunk(hoaDonLayVe.getKhachHangLayVe().getTenKhachHang(), font));
            document.add(customerInfo);

            Paragraph customerInfo2 = new Paragraph();
            customerInfo2.add(new Chunk("CCCD: ", boldFont));
            customerInfo2.add(new Chunk(hoaDonLayVe.getKhachHangLayVe().getCCCD(), font));
            document.add(customerInfo2);

            Paragraph customerInfo3 = new Paragraph();
            customerInfo3.add(new Chunk("Số điện thoại: ", boldFont));
            customerInfo3.add(new Chunk(hoaDonLayVe.getKhachHangLayVe().getSoDienThoai(), font));
            document.add(customerInfo3);


            Paragraph date = new Paragraph();
            date.add(new Chunk("Thời gian lập hóa đơn: ", boldFont));
            date.add(new Chunk(TimeFormat.formatLocalDateTime(hoaDonLayVe.getThoiGianLayVe()), font));
            document.add(date);

            document.add(Chunk.NEWLINE);


            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(100);

            float[] columnWidths = {1f, 3f, 4f, 3f, 3f, 3f};
            table.setWidths(columnWidths);

            String[] headers = {"STT", "Mã vé", "Tên vé", "Tiền vé", "Đã cọc", "Thành tiền"};
            for (String header : headers) {
                PdfPCell headerCell = new PdfPCell(new Phrase(header, boldFont));
                headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                headerCell.setPadding(5);
                table.addCell(headerCell);
            }

            int i = 1;

            for (ChiTietHoaDonLayVe chiTietHoaDonLayVe : hoaDonLayVe.getDanhSachChiTietHoaDonLayVe()) {
                Ve ve = chiTietHoaDonLayVe.getVe();
                PdfPCell cell1 = new PdfPCell(new Phrase(String.valueOf(i), font));
                cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell1.setPadding(5);
                table.addCell(cell1);

                PdfPCell cell2 = new PdfPCell(new Phrase(ve.getMaVe(), font));
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell2.setPadding(5);
                table.addCell(cell2);

                String tenVe = "Chuyến tàu " + ve.getThongTinGaTauDi().getChuyenTau().getMaChuyenTau().substring(0, 4)
                        + " – " + TimeFormat.formatLocalDateTime(ve.getThongTinGaTauDi().getThoiGianDi())
                        + " – " + ve.getThongTinGaTauDi().getGaTau().getTenGaTau()
                        + " – " + ve.getThongTinGaTauDen().getGaTau().getTenGaTau()
                        + " – " + (ve.getLoaiVe().equals(LoaiVe.VECANHAN) ? "Vé cá nhân" : "Vé tập thể");

                PdfPCell cell3 = new PdfPCell(new Phrase(tenVe, font));
                cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell3.setPadding(5);
                table.addCell(cell3);

                PdfPCell cell4 = new PdfPCell(new Phrase(CurrencyFormat.currencyFormat(chiTietHoaDonLayVe.getVeDat().tienVeCuoi()), font));
                cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell4.setPadding(5);
                table.addCell(cell4);

                PdfPCell cell5 = new PdfPCell(new Phrase(CurrencyFormat.currencyFormat(chiTietHoaDonLayVe.getVeDat().tienDatCoc()), font));
                cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell5.setPadding(5);
                table.addCell(cell5);

                PdfPCell cell6 = new PdfPCell(new Phrase(CurrencyFormat.currencyFormat(chiTietHoaDonLayVe.thanhTien()), font));
                cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell6.setPadding(5);
                table.addCell(cell6);

                i++;
            }

            PdfPCell totalLabelCell = new PdfPCell(new Phrase("TỔNG TIỀN", boldFont));
            totalLabelCell.setColspan(5);
            totalLabelCell.setPadding(5);
            totalLabelCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(totalLabelCell);

            PdfPCell totalValueCell = new PdfPCell(new Phrase(CurrencyFormat.currencyFormat(hoaDonLayVe.tongTienCuoi()), boldFont));
            totalValueCell.setPadding(5);
            totalValueCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(totalValueCell);

            document.add(table);


            document.add(new Paragraph("Ghi chú:", boldFont));
            document.add(new Paragraph(" – Hóa đơn này được tạo tự động.", font));
            document.add(new Paragraph(" – Tiền vé đã bao gồm thuế giá trị gia tăng 8%.", font));
            document.add(new Paragraph(" – Hóa đơn không phải là vé và không có giá trị lên tàu.", font));


            document.close();
            System.out.println("Hóa đơn đã được tạo tại: " + filePath);


            File wordFile = new File(filePath);
            if (!wordFile.exists()) {
                return;
            }
            Desktop desktop = Desktop.getDesktop();

            if (desktop.isSupported(Desktop.Action.OPEN)) {
                desktop.open(wordFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        public static void taoHoaDonHuyDatVe(HoaDonHuyDatVe hoaDonHuyDatVe) {
            String filePath = "documents/HoaDonHuyDatVe/" + hoaDonHuyDatVe.getMaHoaDonHuyDatVe() + ".pdf";
            String logoPath = "src/main/resources/images/HoaDon/Logo.png";
            String qrCodePath = "documents/HoaDonHuyDatVe/QRCode/QRCode_" + hoaDonHuyDatVe.getMaHoaDonHuyDatVe() + ".png";
            try {
                // Ensure the directory exists
                Files.createDirectories(Paths.get(filePath).getParent());

                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(filePath));
                document.open();

                String fontPath = "C:\\Windows\\Fonts\\arial.ttf";
                BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                Font font = new Font(baseFont, 12);
                Font boldFont = new Font(baseFont, 12, Font.BOLD);
                Font titleFont = new Font(baseFont, 20, Font.BOLD);

                Image logo = Image.getInstance(logoPath);
                logo.scaleToFit(100, 50);
                logo.setAlignment(Image.ALIGN_CENTER);
                document.add(logo);

                document.add(Chunk.NEWLINE);

                Paragraph title = new Paragraph("HÓA ĐƠN HỦY ĐẶT VÉ", titleFont);
                title.setAlignment(Element.ALIGN_CENTER);
                document.add(title);

                // Mã hóa đơn
                CreateQRCode.generateQRCode(hoaDonHuyDatVe.getMaHoaDonHuyDatVe(), qrCodePath,110 , 110);
                Image qrCode = Image.getInstance(qrCodePath);
                qrCode.setAlignment(Image.ALIGN_CENTER);
                document.add(qrCode);

                Paragraph maHoaDonPar = new Paragraph();
                maHoaDonPar.setAlignment(Element.ALIGN_LEFT);
                maHoaDonPar.add(new Chunk("Mã hóa đơn: ", boldFont));
                maHoaDonPar.add(new Chunk(hoaDonHuyDatVe.getMaHoaDonHuyDatVe(), font));
                document.add(maHoaDonPar);

                document.add(Chunk.NEWLINE);

                // Thông tin công ty
                Paragraph companyInfo = new Paragraph();
                companyInfo.add(new Chunk("Công ty: ", boldFont));
                companyInfo.add(new Chunk("Công ty đường sắt Natri - Natri railway company", font));
                document.add(companyInfo);

                Paragraph companyAddress = new Paragraph();
                companyAddress.add(new Chunk("Địa chỉ: ", boldFont));
                companyAddress.add(new Chunk("Nguyễn Văn Bảo, phường 4, thành phố Hồ Chí Minh", font));
                document.add(companyAddress);

                Paragraph companyInfo2 = new Paragraph();
                companyInfo2.add(new Chunk("Điện thoại: ", boldFont));
                companyInfo2.add(new Chunk("(+84) 375 684 002", font));
                document.add(companyInfo2);

                document.add(Chunk.NEWLINE);

                Paragraph customerInfo = new Paragraph();
                customerInfo.add(new Chunk("Tên khách hàng: ", boldFont));
                customerInfo.add(new Chunk(hoaDonHuyDatVe.getKhachHangHuyDatVe().getTenKhachHang(), font));
                document.add(customerInfo);

                Paragraph customerInfo2 = new Paragraph();
                customerInfo2.add(new Chunk("CCCD: ", boldFont));
                customerInfo2.add(new Chunk(hoaDonHuyDatVe.getKhachHangHuyDatVe().getCCCD(), font));
                document.add(customerInfo2);

                Paragraph customerInfo3 = new Paragraph();
                customerInfo3.add(new Chunk("Số điện thoại: ", boldFont));
                customerInfo3.add(new Chunk(hoaDonHuyDatVe.getKhachHangHuyDatVe().getSoDienThoai(), font));
                document.add(customerInfo3);

                Paragraph date = new Paragraph();
                date.add(new Chunk("Thời gian lập hóa đơn: ", boldFont));
                date.add(new Chunk(TimeFormat.formatLocalDateTime(hoaDonHuyDatVe.getThoiGianHuy()), font));
                document.add(date);

                document.add(Chunk.NEWLINE);

                PdfPTable table = new PdfPTable(6);
                table.setWidthPercentage(100);
                float[] columnWidths = {1f, 3f, 4f, 3f, 3f, 3f};
                table.setWidths(columnWidths);

                String[] headers = {"STT", "Mã vé", "Tên vé", "Tiền vé", "Đã cọc", "Thành tiền"};
                for (String header : headers) {
                    PdfPCell headerCell = new PdfPCell(new Phrase(header, boldFont));
                    headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    headerCell.setPadding(5);
                    table.addCell(headerCell);
                }

                int i = 1;
                for (ChiTietHoaDonHuyDatVe chiTietHoaDonHuyDatVe : hoaDonHuyDatVe.getDanhSachChiTietHoaDonHuyDatVe()) {
                    VeDat veDat = chiTietHoaDonHuyDatVe.getVeDat();
                    PdfPCell cell1 = new PdfPCell(new Phrase(String.valueOf(i), font));
                    cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell1.setPadding(5);
                    table.addCell(cell1);

                    PdfPCell cell2 = new PdfPCell(new Phrase(veDat.getMaVeDat(), font));
                    cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell2.setPadding(5);
                    table.addCell(cell2);

                    String tenVe = "Chuyến tàu " + veDat.getThongTinGaTauDi().getChuyenTau().getMaChuyenTau().substring(0, 4)
                            + " – " + TimeFormat.formatLocalDateTime(veDat.getThongTinGaTauDi().getThoiGianDi())
                            + " – " + veDat.getThongTinGaTauDi().getGaTau().getTenGaTau()
                            + " – " + veDat.getThongTinGaTauDen().getGaTau().getTenGaTau()
                            + " – " + (veDat.getLoaiVe().equals(LoaiVe.VECANHAN) ? "Vé cá nhân" : "Vé tập thể");

                    PdfPCell cell3 = new PdfPCell(new Phrase(tenVe, font));
                    cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell3.setPadding(5);
                    table.addCell(cell3);

                    PdfPCell cell4 = new PdfPCell(new Phrase(CurrencyFormat.currencyFormat(chiTietHoaDonHuyDatVe.getVeDat().tienVeCuoi()), font));
                    cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell4.setPadding(5);
                    table.addCell(cell4);

                    PdfPCell cell5 = new PdfPCell(new Phrase(CurrencyFormat.currencyFormat(chiTietHoaDonHuyDatVe.getVeDat().tienDatCoc()), font));
                    cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell5.setPadding(5);
                    table.addCell(cell5);

                    PdfPCell cell6 = new PdfPCell(new Phrase(CurrencyFormat.currencyFormat(chiTietHoaDonHuyDatVe.soTienHoanLai()), font));
                    cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell6.setPadding(5);
                    table.addCell(cell6);

                    i++;
                }

                PdfPCell totalLabelCell = new PdfPCell(new Phrase("TỔNG TIỀN", boldFont));
                totalLabelCell.setColspan(5);
                totalLabelCell.setPadding(5);
                totalLabelCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(totalLabelCell);

                PdfPCell totalValueCell = new PdfPCell(new Phrase(CurrencyFormat.currencyFormat(hoaDonHuyDatVe.tongTienCuoi()), boldFont));
                totalValueCell.setPadding(5);
                totalValueCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(totalValueCell);

                document.add(table);

                document.add(new Paragraph("Ghi chú:", boldFont));
                document.add(new Paragraph(" – Hóa đơn này được tạo tự động.", font));
                document.add(new Paragraph(" – Tiền vé đã bao gồm thuế giá trị gia tăng 8%.", font));
                document.add(new Paragraph(" – Hóa đơn không phải là vé và không có giá trị lên tàu.", font));

                document.close();
                System.out.println("Hóa đơn đã được tạo tại: " + filePath);

                File wordFile = new File(filePath);
                if (!wordFile.exists()) {
                    return;
                }
                Desktop desktop = Desktop.getDesktop();
                if (desktop.isSupported(Desktop.Action.OPEN)) {
                    desktop.open(wordFile);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    public static void taoHoaDonDatVe(HoaDonDatVe hoaDonDatVe){
        String filePath = "documents/HoaDonDatVe/"+ hoaDonDatVe.getMaHoaDonDatVe() +".pdf";
        String logoPath = "src/main/resources/images/HoaDon/Logo.png";
        String qrCodePath = "documents/HoaDonDatVe/QRCode/QRCode_" + hoaDonDatVe.getMaHoaDonDatVe() + ".png";
        try {
            Rectangle pageSize = new Rectangle(600, 70 * hoaDonDatVe.getDanhSachVeDat().size() + 600);
            Document document = new Document(pageSize);
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();


            String fontPath = "C:\\Windows\\Fonts\\arial.ttf";
            BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            //font thường
            Font font = new Font(baseFont, 12);
            //font in đậm
            Font boldFont = new Font(baseFont, 12, Font.BOLD);

            //font tieu de
            Font titleFont = new Font(baseFont, 20, Font.BOLD);


            Image logo = Image.getInstance(logoPath);
            logo.scaleToFit(100, 50);
            logo.setAlignment(Image.ALIGN_CENTER);
            document.add(logo);


            document.add(Chunk.NEWLINE);


            Paragraph title = new Paragraph("HÓA ĐƠN ĐẶT VÉ", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Mã hóa đơn
            CreateQRCode.generateQRCode(hoaDonDatVe.getMaHoaDonDatVe(), qrCodePath, 80, 80);
            Image qrCode = Image.getInstance(qrCodePath);
            qrCode.setAlignment(Element.ALIGN_LEFT);
            document.add(qrCode);

            Paragraph maHoaDonPar = new Paragraph();
            maHoaDonPar.setAlignment(Element.ALIGN_LEFT);
            maHoaDonPar.add(new Chunk("Mã hóa đơn: ", boldFont));
            maHoaDonPar.add(new Chunk(hoaDonDatVe.getMaHoaDonDatVe(), font));
            document.add(maHoaDonPar);

            document.add(Chunk.NEWLINE);


            // Thông tin công ty
            Paragraph companyInfo = new Paragraph();
            companyInfo.add(new Chunk("Công ty: ", boldFont));
            companyInfo.add(new Chunk("Công ty đường sắt Natri - Natri railway company", font));
            document.add(companyInfo);

            Paragraph companyAddress = new Paragraph();
            companyAddress.add(new Chunk("Địa chỉ: ", boldFont));
            companyAddress.add(new Chunk("Nguyễn Văn Bảo, phường 4, thành phố Hồ Chí Minh", font));
            document.add(companyAddress);

            Paragraph companyInfo2 = new Paragraph();
            companyInfo2.add(new Chunk("Điện thoại: ", boldFont));
            companyInfo2.add(new Chunk("(+84) 375 684 002", font));
            document.add(companyInfo2);

            document.add(Chunk.NEWLINE);

            Paragraph customerInfo = new Paragraph();
            customerInfo.add(new Chunk("Tên khách hàng: ", boldFont));
            customerInfo.add(new Chunk(hoaDonDatVe.getKhachHangDatVe().getTenKhachHang(), font));
            document.add(customerInfo);

            Paragraph customerInfo2 = new Paragraph();
            customerInfo2.add(new Chunk("CCCD: ", boldFont));
            customerInfo2.add(new Chunk(hoaDonDatVe.getKhachHangDatVe().getCCCD(), font));
            document.add(customerInfo2);

            Paragraph customerInfo3 = new Paragraph();
            customerInfo3.add(new Chunk("Số điện thoại: ", boldFont));
            customerInfo3.add(new Chunk(hoaDonDatVe.getKhachHangDatVe().getSoDienThoai(), font));
            document.add(customerInfo3);


            Paragraph date = new Paragraph();
            date.add(new Chunk("Thời gian lập hóa đơn: ", boldFont));
            date.add(new Chunk(TimeFormat.formatLocalDateTime(hoaDonDatVe.getThoiGianLap()), font));
            document.add(date);

            document.add(Chunk.NEWLINE);


            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);

            float[] columnWidths = {1f, 5f, 4f, 3f, 3f};
            table.setWidths(columnWidths);

            String[] headers = {"STT", "Mã vé đặt", "Tên vé đặt", "Tiền vé", "Đặt cọc"};
            for (String header : headers) {
                PdfPCell headerCell = new PdfPCell(new Phrase(header, boldFont));
                headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                headerCell.setPadding(5);
                table.addCell(headerCell);
            }

            int i = 1;

            for (VeDat veDat : hoaDonDatVe.getDanhSachVeDat()) {

                PdfPCell cell1 = new PdfPCell(new Phrase(String.valueOf(i), font));
                cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell1.setPadding(5);
                table.addCell(cell1);

                PdfPCell cell2 = new PdfPCell(new Phrase(veDat.getMaVeDat(), font));
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell2.setPadding(5);
                table.addCell(cell2);

                String tenVe = "Chuyến tàu " + veDat.getThongTinGaTauDi().getChuyenTau().getMaChuyenTau().substring(0, 4)
                        + " – " + TimeFormat.formatLocalDateTime(veDat.getThongTinGaTauDi().getThoiGianDi())
                        + " – " + veDat.getThongTinGaTauDi().getGaTau().getTenGaTau()
                        + " – " + veDat.getThongTinGaTauDen().getGaTau().getTenGaTau()
                        + " – " + (veDat.getLoaiVe().equals(LoaiVe.VECANHAN) ? "Vé cá nhân" : "Vé tập thể");

                PdfPCell cell3 = new PdfPCell(new Phrase(tenVe, font));
                cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell3.setPadding(5);
                table.addCell(cell3);

                PdfPCell cell4 = new PdfPCell(new Phrase(CurrencyFormat.currencyFormat(veDat.tienVeCuoi()), font));
                cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell4.setPadding(5);
                table.addCell(cell4);

                PdfPCell cell5 = new PdfPCell(new Phrase(CurrencyFormat.currencyFormat(veDat.tienDatCoc()), font));
                cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell5.setPadding(5);
                table.addCell(cell5);

                i++;
            }

            PdfPCell totalLabelCell = new PdfPCell(new Phrase("TỔNG TIỀN", font));
            totalLabelCell.setColspan(3);
            totalLabelCell.setPadding(5);
            totalLabelCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(totalLabelCell);

            PdfPCell totalValueCell = new PdfPCell(new Phrase(CurrencyFormat.currencyFormat(hoaDonDatVe.tongTienCuoi()), font));
            totalValueCell.setColspan(2);
            totalValueCell.setPadding(5);
            totalValueCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(totalValueCell);

            PdfPCell tienDatCocCell = new PdfPCell(new Phrase("TỔNG TIỀN ĐẶT CỌC", boldFont));
            tienDatCocCell.setColspan(3);
            tienDatCocCell.setPadding(5);
            tienDatCocCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(tienDatCocCell);

            PdfPCell giaTriTienDatCocCell = new PdfPCell(new Phrase(CurrencyFormat.currencyFormat(hoaDonDatVe.tongTienDatCoc()), boldFont));
            giaTriTienDatCocCell.setColspan(2);
            giaTriTienDatCocCell.setPadding(5);
            giaTriTienDatCocCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(giaTriTienDatCocCell);

            document.add(table);


            document.add(new Paragraph("Ghi chú:", boldFont));
            document.add(new Paragraph(" – Hóa đơn này được tạo tự động.", font));
            document.add(new Paragraph(" – Tiền vé đã bao gồm thuế giá trị gia tăng 8%.", font));
            document.add(new Paragraph(" – Hóa đơn không phải là vé và không có giá trị lên tàu.", font));


            document.close();
            System.out.println("Hóa đơn đã được tạo tại: " + filePath);


            File wordFile = new File(filePath);
            if (!wordFile.exists()) {
                return;
            }
            Desktop desktop = Desktop.getDesktop();

            if (desktop.isSupported(Desktop.Action.OPEN)) {
                desktop.open(wordFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void taoHoaDonDoiVe(HoaDonDoiVe hoaDonDoiVe,ChiTietVe ctVeCu){
        String filePath = "documents/HoaDonDoiVe/"+ hoaDonDoiVe.getMaHoaDonDoiVe() +".pdf";
        String logoPath = "src/main/resources/images/HoaDon/Logo.png";
        String qrCodePath = "documents/HoaDonDoiVe/QRCode/QRCode_" + hoaDonDoiVe.getMaHoaDonDoiVe() + ".png";
        try {

            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();


            String fontPath = "C:\\Windows\\Fonts\\arial.ttf";
            BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            //font thường
            Font font = new Font(baseFont, 12);
            //font in đậm
            Font boldFont = new Font(baseFont, 12, Font.BOLD);

            //font tieu de
            Font titleFont = new Font(baseFont, 20, Font.BOLD);


            Image logo = Image.getInstance(logoPath);
            logo.scaleToFit(100, 50);
            logo.setAlignment(Image.ALIGN_CENTER);
            document.add(logo);


            document.add(Chunk.NEWLINE);


            Paragraph title = new Paragraph("HÓA ĐƠN ĐỔI VÉ", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Mã hóa đơn
            CreateQRCode.generateQRCode(hoaDonDoiVe.getMaHoaDonDoiVe(), qrCodePath, 80, 80);
            Image qrCode = Image.getInstance(qrCodePath);
            qrCode.setAlignment(Element.ALIGN_LEFT);
            document.add(qrCode);

            Paragraph maHoaDonPar = new Paragraph();
            maHoaDonPar.setAlignment(Element.ALIGN_LEFT);
            maHoaDonPar.add(new Chunk("Mã hóa đơn: ", boldFont));
            maHoaDonPar.add(new Chunk(hoaDonDoiVe.getMaHoaDonDoiVe(), font));
            document.add(maHoaDonPar);

            document.add(Chunk.NEWLINE);


            // Thông tin công ty
            Paragraph companyInfo = new Paragraph();
            companyInfo.add(new Chunk("Công ty: ", boldFont));
            companyInfo.add(new Chunk("Công ty đường sắt Natri - Natri railway company", font));
            document.add(companyInfo);

            Paragraph companyAddress = new Paragraph();
            companyAddress.add(new Chunk("Địa chỉ: ", boldFont));
            companyAddress.add(new Chunk("Nguyễn Văn Bảo, phường 4, thành phố Hồ Chí Minh", font));
            document.add(companyAddress);

            Paragraph companyInfo2 = new Paragraph();
            companyInfo2.add(new Chunk("Điện thoại: ", boldFont));
            companyInfo2.add(new Chunk("(+84) 375 684 002", font));
            document.add(companyInfo2);

            document.add(Chunk.NEWLINE);

            Paragraph customerInfo = new Paragraph();
            customerInfo.add(new Chunk("Tên khách hàng: ", boldFont));
            customerInfo.add(new Chunk(ctVeCu.getKhachHang().getTenKhachHang(), font));
            document.add(customerInfo);

            Paragraph customerInfo2 = new Paragraph();
            customerInfo2.add(new Chunk("CCCD: ", boldFont));
            customerInfo2.add(new Chunk(ctVeCu.getKhachHang().getCCCD(), font));
            document.add(customerInfo2);

            Paragraph customerInfo3 = new Paragraph();
            customerInfo3.add(new Chunk("Số điện thoại: ", boldFont));
            customerInfo3.add(new Chunk(ctVeCu.getKhachHang().getSoDienThoai(), font));
            document.add(customerInfo3);


            Paragraph date = new Paragraph();
            date.add(new Chunk("Thời gian lập hóa đơn: ", boldFont));
            date.add(new Chunk(TimeFormat.formatLocalDateTime(hoaDonDoiVe.getThoiGianDoiVe()), font));
            document.add(date);

            document.add(Chunk.NEWLINE);


            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);

            float[] columnWidths = {1f, 5f, 6f, 4f};
            table.setWidths(columnWidths);

            String[] headers = {"STT", "Mã vé", "Tên vé", "Tiền vé"};
            for (String header : headers) {
                PdfPCell headerCell = new PdfPCell(new Phrase(header, boldFont));
                headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                headerCell.setPadding(5);
                table.addCell(headerCell);
            }

            int i = 1;


            PdfPCell cell1 = new PdfPCell(new Phrase(String.valueOf(i), font));
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setPadding(5);
            table.addCell(cell1);

            PdfPCell cell2 = new PdfPCell(new Phrase(hoaDonDoiVe.getVeMoi().getMaVe(), font));
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setPadding(5);
            table.addCell(cell2);

            String tenVe = "Chuyến tàu " + hoaDonDoiVe.getVeMoi().getThongTinGaTauDi().getChuyenTau().getMaChuyenTau().substring(0, 4)
                    + " – " + TimeFormat.formatLocalDateTime(hoaDonDoiVe.getVeMoi().getThongTinGaTauDi().getThoiGianDi())
                    + " – " + hoaDonDoiVe.getVeMoi().getThongTinGaTauDi().getGaTau()
                    + " – " + hoaDonDoiVe.getVeMoi().getThongTinGaTauDen().getGaTau()
                    + " – " + (hoaDonDoiVe.getVeMoi().getLoaiVe().equals(LoaiVe.VECANHAN) ? "Vé cá nhân" : "Vé tập thể");

            PdfPCell cell3 = new PdfPCell(new Phrase(tenVe, font));
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell3.setPadding(5);
            table.addCell(cell3);



            PdfPCell cell4 = new PdfPCell(new Phrase(CurrencyFormat.currencyFormat(hoaDonDoiVe.tongTienCuoi()), font));
            cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell4.setPadding(5);
            table.addCell(cell4);

            i++;
            // }

            PdfPCell totalLabelCell = new PdfPCell(new Phrase("TỔNG TIỀN", boldFont));
            totalLabelCell.setColspan(3);
            totalLabelCell.setPadding(5);
            totalLabelCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(totalLabelCell);

            PdfPCell totalValueCell = new PdfPCell(new Phrase(CurrencyFormat.currencyFormat(hoaDonDoiVe.tongTienCuoi()), boldFont));
            totalValueCell.setPadding(5);
            totalValueCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(totalValueCell);

            document.add(table);


            document.add(new Paragraph("Ghi chú:", boldFont));
            document.add(new Paragraph(" – Hóa đơn này được tạo tự động.", font));
            document.add(new Paragraph(" – Tiền vé đã bao gồm thuế giá trị gia tăng 8%.", font));
            document.add(new Paragraph(" – Hóa đơn không phải là vé và không có giá trị lên tàu.", font));


            document.close();
            System.out.println("Hóa đơn đã được tạo tại: " + filePath);


            File wordFile = new File(filePath);
            if (!wordFile.exists()) {
                return;
            }
            Desktop desktop = Desktop.getDesktop();

            if (desktop.isSupported(Desktop.Action.OPEN)) {
                desktop.open(wordFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void taoHoaDonHuyVe(HoaDonHuyVe hoaDonHuyVe) {
        String filePath = "documents/HoaDonHuyVe/" + hoaDonHuyVe.getMaHoaDonHuyVe() + ".pdf";
        String logoPath = "src/main/resources/images/HoaDon/Logo.png";
        String qrCodePath = "documents/HoaDonHuyVe/QRCode/QRCode_" + hoaDonHuyVe.getMaHoaDonHuyVe() + ".png";
        try {
            // Ensure the directory exists
            Files.createDirectories(Paths.get(filePath).getParent());

            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            String fontPath = "C:\\Windows\\Fonts\\arial.ttf";
            BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font font = new Font(baseFont, 12);
            Font boldFont = new Font(baseFont, 12, Font.BOLD);
            Font titleFont = new Font(baseFont, 20, Font.BOLD);

            Image logo = Image.getInstance(logoPath);
            logo.scaleToFit(100, 50);
            logo.setAlignment(Image.ALIGN_CENTER);
            document.add(logo);

            document.add(Chunk.NEWLINE);

            Paragraph title = new Paragraph("HÓA ĐƠN HỦY ĐẶT VÉ", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Mã hóa đơn
            CreateQRCode.generateQRCode(hoaDonHuyVe.getMaHoaDonHuyVe(), qrCodePath,110 , 110);
            Image qrCode = Image.getInstance(qrCodePath);
            qrCode.setAlignment(Image.ALIGN_CENTER);
            document.add(qrCode);

            Paragraph maHoaDonPar = new Paragraph();
            maHoaDonPar.setAlignment(Element.ALIGN_LEFT);
            maHoaDonPar.add(new Chunk("Mã hóa đơn: ", boldFont));
            maHoaDonPar.add(new Chunk(hoaDonHuyVe.getMaHoaDonHuyVe(), font));
            document.add(maHoaDonPar);

            document.add(Chunk.NEWLINE);

            // Thông tin công ty
            Paragraph companyInfo = new Paragraph();
            companyInfo.add(new Chunk("Công ty: ", boldFont));
            companyInfo.add(new Chunk("Công ty đường sắt Natri - Natri railway company", font));
            document.add(companyInfo);

            Paragraph companyAddress = new Paragraph();
            companyAddress.add(new Chunk("Địa chỉ: ", boldFont));
            companyAddress.add(new Chunk("Nguyễn Văn Bảo, phường 4, thành phố Hồ Chí Minh", font));
            document.add(companyAddress);

            Paragraph companyInfo2 = new Paragraph();
            companyInfo2.add(new Chunk("Điện thoại: ", boldFont));
            companyInfo2.add(new Chunk("(+84) 375 684 002", font));
            document.add(companyInfo2);

            document.add(Chunk.NEWLINE);

            Paragraph customerInfo = new Paragraph();
            customerInfo.add(new Chunk("Tên khách hàng: ", boldFont));
            customerInfo.add(new Chunk(hoaDonHuyVe.getKhachHangHuyVe().getTenKhachHang(), font));
            document.add(customerInfo);

            Paragraph customerInfo2 = new Paragraph();
            customerInfo2.add(new Chunk("CCCD: ", boldFont));
            customerInfo2.add(new Chunk(hoaDonHuyVe.getKhachHangHuyVe().getCCCD(), font));
            document.add(customerInfo2);

            Paragraph customerInfo3 = new Paragraph();
            customerInfo3.add(new Chunk("Số điện thoại: ", boldFont));
            customerInfo3.add(new Chunk(hoaDonHuyVe.getKhachHangHuyVe().getSoDienThoai(), font));
            document.add(customerInfo3);

            Paragraph date = new Paragraph();
            date.add(new Chunk("Thời gian lập hóa đơn: ", boldFont));
            date.add(new Chunk(TimeFormat.formatLocalDateTime(hoaDonHuyVe.getThoiGianHuyVe()), font));
            document.add(date);

            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(100);
            float[] columnWidths = {1f, 3f, 4f, 3f, 3f, 3f};
            table.setWidths(columnWidths);

            String[] headers = {"STT", "Mã vé", "Tên vé", "Tiền vé", "Đã cọc", "Thành tiền"};
            for (String header : headers) {
                PdfPCell headerCell = new PdfPCell(new Phrase(header, boldFont));
                headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                headerCell.setPadding(5);
                table.addCell(headerCell);
            }

            int i = 1;
            for (ChiTietHoaDonHuyVe chiTietHoaDonHuyVe : hoaDonHuyVe.getDanhSachChiTietHoaDonHuyVe()) {
                Ve ve = chiTietHoaDonHuyVe.getVe();
                PdfPCell cell1 = new PdfPCell(new Phrase(String.valueOf(i), font));
                cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell1.setPadding(5);
                table.addCell(cell1);

                PdfPCell cell2 = new PdfPCell(new Phrase(ve.getMaVe(), font));
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell2.setPadding(5);
                table.addCell(cell2);

                String tenVe = "Chuyến tàu " + ve.getThongTinGaTauDi().getChuyenTau().getMaChuyenTau().substring(0, 4)
                        + " – " + TimeFormat.formatLocalDateTime(ve.getThongTinGaTauDi().getThoiGianDi())
                        + " – " + ve.getThongTinGaTauDi().getGaTau().getTenGaTau()
                        + " – " + ve.getThongTinGaTauDen().getGaTau().getTenGaTau()
                        + " – " + (ve.getLoaiVe().equals(LoaiVe.VECANHAN) ? "Vé cá nhân" : "Vé tập thể");

                PdfPCell cell3 = new PdfPCell(new Phrase(tenVe, font));
                cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell3.setPadding(5);
                table.addCell(cell3);

                PdfPCell cell4 = new PdfPCell(new Phrase(CurrencyFormat.currencyFormat(chiTietHoaDonHuyVe.getVe().tienVeCuoi()), font));
                cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell4.setPadding(5);
                table.addCell(cell4);


                PdfPCell cell6 = new PdfPCell(new Phrase(CurrencyFormat.currencyFormat(chiTietHoaDonHuyVe.soTienHoanLai()), font));
                cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell6.setPadding(5);
                table.addCell(cell6);

                i++;
            }

            PdfPCell totalLabelCell = new PdfPCell(new Phrase("TỔNG TIỀN", boldFont));
            totalLabelCell.setColspan(5);
            totalLabelCell.setPadding(5);
            totalLabelCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(totalLabelCell);

            PdfPCell totalValueCell = new PdfPCell(new Phrase(CurrencyFormat.currencyFormat(hoaDonHuyVe.tongTienCuoi()), boldFont));
            totalValueCell.setPadding(5);
            totalValueCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(totalValueCell);

            document.add(table);

            document.add(new Paragraph("Ghi chú:", boldFont));
            document.add(new Paragraph(" – Hóa đơn này được tạo tự động.", font));
            document.add(new Paragraph(" – Tiền vé đã bao gồm thuế giá trị gia tăng 8%.", font));
            document.add(new Paragraph(" – Hóa đơn không phải là vé và không có giá trị lên tàu.", font));

            document.close();
            System.out.println("Hóa đơn đã được tạo tại: " + filePath);

            File wordFile = new File(filePath);
            if (!wordFile.exists()) {
                return;
            }
            Desktop desktop = Desktop.getDesktop();
            if (desktop.isSupported(Desktop.Action.OPEN)) {
                desktop.open(wordFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    }
