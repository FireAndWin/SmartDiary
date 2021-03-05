package com.SmartDiary.Utils;

import androidx.annotation.Nullable;

import java.util.Calendar;
import java.util.Date;

public class TimeUtils {

    public static String long_2_MonthDay(long timeMillis,@Nullable String format){
        if (format==null||format=="") {
            // Date -- String
            // 创建日期对象
            Date date = new Date(timeMillis);
            Calendar calendar=Calendar.getInstance();
            calendar.setTime(date);

            return ""+calendar.get(Calendar.MONTH)+"."+calendar.get(Calendar.DAY_OF_MONTH);
        }
        return "";
    }

    public static String long_2_WeekDay(long timeMillis,@Nullable String format){
        if (format==null||format=="") {
            // Date -- String
            // 创建日期对象
            Date date = new Date(timeMillis);
            Calendar calendar=Calendar.getInstance();
            calendar.setTime(date);

            return ""+calendar.get(Calendar.DAY_OF_WEEK);
        }
        return "";
    }


}
