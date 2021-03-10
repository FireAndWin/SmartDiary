package com.SmartDiary.pojo;

import java.util.HashMap;
import java.util.Map;

//表示某一天所记录的数据
public class DayEntry {

    //表示某一天的时间;
    public long date;
    /*是个Map,key是记录项id,value是记录结果字符串*/
    /*
    * 22:睡得很早
    * 24:300分钟*/
    public Map<String,String> map;

    //这个构造方法中也是放入点测试数据.
    public DayEntry() {
        date=System.currentTimeMillis();
        map=new HashMap<>();
        map.put("21","睡得很早");
        map.put("22","今天玩得很开心");
        map.put("23","51");
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }
}
