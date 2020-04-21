package app.basic.Date;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class DateTest {
    public static String longToDate(long millis) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(millis);;
        return cal.getTime().toString();
    }

    public static void main(String[] args) throws InterruptedException {
        LocalDate ld = LocalDate.now();
        LocalTime t = LocalTime.now();
        LocalDateTime lDT = LocalDateTime.now();
        DateTimeFormatter dTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        System.out.println(lDT.format(dTF));
        System.out.println(longToDate(11111111110L));
    }
}