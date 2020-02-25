package utils;

import java.time.format.DateTimeFormatter;

public class DateUtils {

    public static DateTimeFormatter getStandardFormatter()
    {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy");
    }

}
