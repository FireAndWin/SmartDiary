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
    //用来分析数据的js代码;
    public String analysis_js;
    //展示类的视图,但是只用来展示一天的数据
    public String separate_js;
    //也是展示类的视图,用来做总的可视化,可选
    public String continuous_view;
    //类型信息,方便可视化
    public String template_class;


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }

    public String getEdit_view() {
        return edit_view;
    }

    public String getRecord_view() {
        return record_view;
    }

    public String getContinuous_view() {
        return continuous_view;
    }


    public String getTemplate_class() {
        return template_class;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setEdit_view(String edit_view) {
        this.edit_view = edit_view;
    }

    public void setRecord_view(String record_view) {
        this.record_view = record_view;
    }

    public void setTemplate_class(String template_class) {
        this.template_class = template_class;
    }

    public void setContinuous_view(String continuous_view) {
        this.continuous_view = continuous_view;
    }
}
