package com.SmartDiary.pojo;

public class RecordTemplate {
    public String id;

    //模板名称
    public String name;
    //模板备注信息
    public String info;
    //进行编辑的视图,用来产生实际的记录项
    public String edit_view;
    //编辑视图,就是记录项具体的记录视图
    public String record_view;
    //展示类的视图,但是只用来展示一天的数据
    public String single_display_view;
    //也是展示类的视图,用来做总的可视化,可选
    public String total_chart_view;
    //类型信息,方便可视化
    public String template_class;
}
