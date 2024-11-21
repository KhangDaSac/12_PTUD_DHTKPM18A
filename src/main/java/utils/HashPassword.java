package utils;

import java.security.MessageDigest;
import java.util.Base64;

public class HashPassword {
    public static String hashPassword(String password){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            byte[] hashBytes = digest.digest(password.getBytes());

            String hashedPassword = Base64.getEncoder().encodeToString(hashBytes);

            return hashedPassword;
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(hashPassword("Khang"));
    }
}
