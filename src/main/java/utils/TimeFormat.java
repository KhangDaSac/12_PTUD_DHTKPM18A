package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeFormat {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");

    public static String formatLocalDateTime(LocalDateTime localDateTime){
        return localDateTime.format(formatter);
    }
}
