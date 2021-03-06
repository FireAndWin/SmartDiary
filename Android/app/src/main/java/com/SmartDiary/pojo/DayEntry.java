package com.SmartDiary.pojo;

import java.util.Map;

//表示某一天所记录的数据
public class DayEntry {

    //表示某一天的数据
    public long date;
    /*是个Map,key是记录项id,value是记录结果字符串*/
    public Map<String,String> map;

}
