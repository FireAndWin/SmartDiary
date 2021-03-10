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
    //和模板相关的变量
    //格式字符串,将这个交给模板的record_view生成具体的编辑控件
    public String format;
    //这个记录项的模板id
    public String template_id;
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



    public void setName(String name) {
        this.name = name;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIcon_path(String icon_path) {
        this.icon_path = icon_path;
    }

    public void setLatest(long latest) {
        this.latest = latest;
    }

    public String getAnalysis_result() {
        return analysis_result;
    }

    public void setAnalysis_result(String analysis_result) {
        this.analysis_result = analysis_result;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getLocked() {
        return locked;
    }

    public void setLocked(int locked) {
        this.locked = locked;
    }

    public String getAlarm() {
        return alarm;
    }

    public void setAlarm(String alarm) {
        this.alarm = alarm;
    }

    public String getArg1() {
        return arg1;
    }

    public void setArg1(String arg1) {
        this.arg1 = arg1;
    }

    public String getArg2() {
        return arg2;
    }

    public void setArg2(String arg2) {
        this.arg2 = arg2;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

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

    public RecordEntry(String name, String info, String id,String template_id) {
        this.name = name;
        this.info = info;
        this.id = id;
        setTemplate_id(template_id);
        format="";
        analysis_result="";
    }

}
