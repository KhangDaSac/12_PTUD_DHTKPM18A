package utils;

import java.text.DecimalFormat;

public class CurrencyFormat {
    public static DecimalFormat decimalFormat = new DecimalFormat("#,### 'VNĐ'");
    public static String currencyFormat(double amount){
        return decimalFormat.format(amount);
    }
}
