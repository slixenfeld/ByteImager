package de.Utility;

import java.util.Calendar;
import java.util.Date;

public class Util {

    public static int imageSize = 0xFF;

    public static void log(String line) {
        Date time = Calendar.getInstance().getTime();
        System.out.println(time + " " + line);
    }

    public static int  getBit(int n, int k) {
        return (n >> k) & 1;
    }
}
