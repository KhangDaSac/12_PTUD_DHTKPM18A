package DataGeneration;



import com.github.javafaker.Address;
import com.github.javafaker.Faker;

import java.util.Locale;

public class PhatSinhDuLieu {
    public static void main(String[] args) {
        // Khởi tạo Faker với ngôn ngữ tiếng Việt
        Faker faker = new Faker(new Locale("vi"));

        // Phát sinh dữ liệu
        for (int i = 0; i < 5; i++) {
            String ten = faker.name().fullName();            // Tên tiếng Việt
            String diaChi = faker.address().streetName();   // Địa chỉ tiếng Việt
            String soDienThoai = faker.phoneNumber().phoneNumber(); // Số điện thoại tiếng Việt

            // In dữ liệu
            System.out.println("Tên: " + ten);
            System.out.println("Địa chỉ: " + diaChi);
            System.out.println("Số điện thoại: " + soDienThoai);
            System.out.println("----------------------");
        }
    }
}
