package DataGeneration;

import com.github.javafaker.Faker;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import java.io.IOException;
import java.util.*;

public class LayDiaChiNhaHang {
    private static final String[] MA_TINH = {
            "001", "002", "004", "006", "008", "010", "011", "012", "014", "015",
            "017", "019", "020", "022", "024", "025", "026", "027", "030", "031",
            "033", "034", "035", "036", "037", "038", "040", "042", "044", "045",
            "046", "048", "049", "051", "052", "054", "056", "058", "060", "062",
            "064", "066", "067", "068", "070", "072", "074", "075", "077", "079",
            "080", "082", "083", "084", "086", "087", "089", "091", "092", "093",
            "094", "095", "096"
    };

    private static final Random random = new Random();
    public static void main(String[] args) {

        int j = 0;
        ArrayList<String> listDiaChi = new ArrayList<String>();
        try {
            // URL của trang cần lấy dữ liệu
            String url = "https://www.yellowpages.vn/tgcls/30200710/danh-s%C3%A1ch-nh%C3%A0-h%C3%A0ng-vi%E1%BB%87t-nam.html";

            for (int i = 1; i <= 10; i++) { // Giả sử có 10 trang
                Document doc = Jsoup.connect(url + i).get();

                // Chọn tất cả các phần tử chứa địa chỉ nhà hàng
                Elements diaChiElements = doc.select(".div_listing div.yp_noidunglistings p small"); // Chọn tất cả phần tử <small>

                // Lặp qua từng địa chỉ và in ra
                for (Element diaChiElement : diaChiElements) {
                    // Lấy văn bản bên trong phần tử <small>
                    String diaChi = diaChiElement.text();
                    String regex = "^(\\d+|Số|Km|Phòng|QL|Căn|Khu|Ngã|Tầng|Lô).*";
                    if(diaChi.matches(regex)){
                        listDiaChi.add(diaChi);
                        j++;
                    }
                    // In địa chỉ ra màn hình

                }
            }
            System.out.println(j);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Faker faker = new Faker(new Locale("vi"));
        List<String> danhSachCCCD = generateUniqueCCCD(300);
        // Phát sinh dữ liệu


        int[] arr = new int[5];

        Random random = new Random();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (int i = 0; i < 300; i++) {
            String ten = faker.name().fullName();
            String diaChi = listDiaChi.get(i);
            String soDienThoai = faker.phoneNumber().phoneNumber();
            int ran = random.nextInt(5);
            String maKhachHang = "KH" + (20 + ran) + String.format("%06d",arr[ran] + 1);
            arr[ran]++;
            LocalDate birthDate = faker.date().birthday(4, 65).toInstant()
                    .atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            int tuoi = calculateAge(birthDate);
            String maLoaiKH = null;
            if(tuoi <= 6){
                maLoaiKH = "TE";
            }else if(tuoi <= 22){
                double randomNum = random.nextDouble();
                if(randomNum < 0.92){
                    maLoaiKH = "HS";
                }else{
                    maLoaiKH = "TT";
                }
            }else if (tuoi >= 60){
                maLoaiKH = "CT";
            }else{
                double randomNum = random.nextDouble();
                if(randomNum < 0.08){
                    maLoaiKH = "TB";
                }else{
                    maLoaiKH = "TT";
                }
            }

            // In dữ liệu
//            System.out.println("Mã khách hàng: " + maKhachHang);
//            System.out.println("CCCD: " + danhSachCCCD.get(i));
//            System.out.println("Tên: " + ten);
//            System.out.println("Địa chỉ: " + diaChi);
//            System.out.println("Số điện thoại: " + soDienThoai.substring(0, 10));
//            System.out.println("Ngày sinh: " + birthDate.toString());
//            System.out.println("Loại khách hàng: " + maLoaiKH);
            System.out.println("insert into KhachHang values ('" + maKhachHang+ "', '" + danhSachCCCD.get(i) +"', '" + ten + "', '" + birthDate.toString() +"', '" + soDienThoai.replaceAll(" ", "").substring(0, 10) + "', '" + maLoaiKH + "')");

        }
    }
    public static List<String> generateUniqueCCCD(int count) {
        Set<String> cccdSet = new HashSet<>();

        while (cccdSet.size() < count) {
            String cccd = generateCCCD();
            cccdSet.add(cccd);
        }

        return new ArrayList<>(cccdSet); // Chuyển đổi Set thành List
    }

    public static String generateCCCD() {
        String maTinh = MA_TINH[random.nextInt(MA_TINH.length)];
        String genderCode = generateGenderCode();
        String yearOfBirth = String.format("%02d", random.nextInt(100)); // 2 số cuối của năm sinh
        String randomCode = String.format("%06d", random.nextInt(1000000)); // Mã số ngẫu nhiên từ 000001 đến 999999

        return maTinh + genderCode + yearOfBirth + randomCode;
    }

    public static String generateGenderCode() {
        int century = random.nextInt(2); // 0 - Thế kỷ XX, 1 - Thế kỷ XXI
        return (century == 0 ? (random.nextBoolean() ? "0" : "1") : (random.nextBoolean() ? "2" : "3"));
    }

    public static int calculateAge(LocalDate birthDate) {
        LocalDate today = LocalDate.now();
        if (birthDate != null && (birthDate.isBefore(today) || birthDate.isEqual(today))) {
            return Period.between(birthDate, today).getYears();
        } else {
            throw new IllegalArgumentException("Ngày sinh không hợp lệ");
        }
    }


}


