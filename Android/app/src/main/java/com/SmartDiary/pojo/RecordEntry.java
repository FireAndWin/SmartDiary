package com.SmartDiary.pojo;
//代表一个具体的可以记录的记录项
public class RecordEntry {
    //记录项名称
    public String name;
    //记录项备注信息
    public String info;
    //id
    public String id;
    //图标
    public String icon_path;
    //最近记录时间
    public long latest;
    //上次分析得到的结果
    public String analysis_result;
    //--------拓展功能-------
    //记录频次,单位是天,默认是每天
    public int frequency;
    //是否被密码保护,因为布尔值好像是根据int储存的,直接就写成int了
    public int locked;
    //提醒时间设置,可以直接写成一个json节省地方
    public String alarm;
    //备用参数1
    public String arg1;
    //备用参数2
    public String arg2;


    //和模板相关的变量
    //格式字符串,将这个交给模板的record_view生成具体的编辑控件
    public String format;
    //这个记录项的模板id
    public String template_id;


    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }

    public String getId() {
        return id;
    }

    public String getIcon_path() {
        return icon_path;
    }

    public long getLatest() {
        return latest;
    }

    public String getFormat() {
        return format;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public RecordEntry(String name, String info, String id) {
        this.name = name;
        this.info = info;
        this.id = id;
    }
}
