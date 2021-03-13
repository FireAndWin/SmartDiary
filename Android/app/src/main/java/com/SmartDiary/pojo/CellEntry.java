package com.SmartDiary.pojo;

/*表示最基本的表格单元,有两个值,一个是日期,一个是具体的记录值*/
public class CellEntry {
    public long date;
    public String value;

    public CellEntry(long date, String value) {
        this.date = date;
        this.value = value;
    }


    public CellEntry() {
    }

    public long getDate() {
        return date;
    }

    public String getValue() {
        return value;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
