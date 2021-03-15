package com.SmartDiary.Utils;

import androidx.annotation.Nullable;

import java.util.Calendar;
import java.util.Date;

public class TimeUtilsMy {

    public static long get_today_long(){
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    /*
    * 获取距离今天的指定天数的日期的毫秒数,
    * 进行了小时和分钟和毫秒的归零操作,
    * -1就是前一天,1就是明天,0就是今天的*/
    public static long get_deltaTime_long(int amount){
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DAY_OF_MONTH,amount);
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
    
    public static String long_2_MonthDay(long timeMillis){
        Calendar calendar=Calendar.getInstance();
        calendar.setTimeInMillis(timeMillis);
        return ""+calendar.get(Calendar.MONTH)+"."+calendar.get(Calendar.DAY_OF_MONTH);
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
