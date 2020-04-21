package app.basic.Date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTest {
    public static void main(String[] args) throws InterruptedException {
        LocalDate ld = LocalDate.now();
        LocalTime t = LocalTime.now();
        LocalDateTime lDT = LocalDateTime.now();
        DateTimeFormatter dTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(lDT.format(dTF));
        Thread.sleep(2000);
        System.out.println(lDT.format(dTF));
    }
}