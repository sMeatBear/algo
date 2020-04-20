package app.basic;

import java.io.File;
import java.util.Calendar;

public class FileSeparator {
    public static void main(String[] args) {
        System.out.println(File.separator);
        System.out.println(File.pathSeparator);
        System.out.println(File.separatorChar);
        long time = 1587357987 * 1000;
        System.out.println("time: " + time);
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        System.out.println(cal.getTime());
    }
}