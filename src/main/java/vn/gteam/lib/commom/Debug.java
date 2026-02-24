package vn.gteam.lib.commom;

import vn.gteam.lib.CalendarUtils;

public class Debug {
    public static void log(String text){
        System.out.println(CalendarUtils.getTimeStringCurr() + " | " + text);
    }

    public static void log(int number){
        Debug.log(String.valueOf(number));
    }

    public static void warning(String text){
        System.err.println(CalendarUtils.getTimeStringCurr() + " | " + text);
    }

    public static void log(Object instance, int size){
        Debug.log("loader Loader class = " + instance.getClass().getSimpleName() + " ; size = " + size);
    }
}