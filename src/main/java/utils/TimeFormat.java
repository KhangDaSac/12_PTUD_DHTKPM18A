package utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeFormat {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
    private static DateTimeFormatter formatterLocalDateSQL = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static DateTimeFormatter formatterLocalDateTimeSQL = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm");
    private static DateTimeFormatter formatterLocalDateNumber = DateTimeFormatter.ofPattern("ddMMyyyy");
    public static String formatLocalDateTime(LocalDateTime localDateTime){
        return localDateTime.format(formatter);
    }
    public static String formatLocalDateSQL(LocalDate localDate){
        return localDate.format(formatterLocalDateSQL);
    }

    public static String formatLocalDateNumber(LocalDate localDate){
        return localDate.format(formatterLocalDateNumber);
    }

    public static String formatLocalDateTimeSQL(LocalDateTime localDateTime){
        return localDateTime.format(formatterLocalDateTimeSQL);
    }

}
