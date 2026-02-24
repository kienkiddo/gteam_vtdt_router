package vn.gteam.lib;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarUtils {
    public static String getTimeStringCurr(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
    }

    public static int getDayInWeek(){
        return  Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
    }

    public static int getHourInDay(){
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    }
}
