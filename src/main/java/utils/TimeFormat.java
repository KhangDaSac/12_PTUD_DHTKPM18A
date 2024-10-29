package utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeFormat {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
    private static DateTimeFormatter formatterLocalDateSQL = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static DateTimeFormatter formatterLocalDateNumber = DateTimeFormatter.ofPattern("ddMMyy");
    public static String formatLocalDateTime(LocalDateTime localDateTime){
        return localDateTime.format(formatter);
    }
    public static String formatLocalDateTimeSQL(LocalDate localDate){
        return localDate.format(formatterLocalDateSQL);
    }

    public static String formatLocalDateTimeNumber(LocalDate localDate){
        return localDate.format(formatterLocalDateNumber);
    }
}
