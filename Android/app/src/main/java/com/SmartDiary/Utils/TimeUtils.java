package com.SmartDiary.Utils;

import androidx.annotation.Nullable;

import java.util.Calendar;
import java.util.Date;

public class TimeUtils {

    public static long get_today_long(){
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    public static long get_dateTime_long(int YEAR,int MONTH,int MONTH_DAY){
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.YEAR,YEAR);
        calendar.set(Calendar.MONTH,MONTH);
        calendar.set(Calendar.DAY_OF_MONTH,MONTH_DAY);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }
    
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
