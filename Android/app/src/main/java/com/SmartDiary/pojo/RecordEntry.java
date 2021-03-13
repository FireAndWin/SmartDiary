package com.SmartDiary.pojo;
//代表一个具体的可以记录的记录项
public class RecordEntry {
    //id
    public String id;
    //记录项名称
    public String name;
    //记录项描述信息
    public String info;
    //图标
    public String icon;
    //最近记录时间
    public long latest;
    //上次分析得到的结果
    public String analysis_result;


    //和模板相关的变量
    //这个记录项的模板id
    public String template_id;
    //格式字符串,将这个交给模板的record_view生成具体的编辑控件
    public String format;
    //进行编辑的视图,用来产生实际的记录项
    public String edit_view;
    //编辑视图,就是记录项具体的记录视图
    public String record_view;
    //展示类的视图,但是只用来展示一天的数据
    public String separate_js;
    //也是展示类的视图,用来做总的可视化,可选
    public String continuous_view;


    //--------拓展功能-------
    //表示状态的变量
    public int status;
    //扩展参数
    public String arg;


    public RecordEntry() {
    }

    public String getEdit_view() {
        return edit_view;
    }

    public void setEdit_view(String edit_view) {
        this.edit_view = edit_view;
    }

    public String getRecord_view() {
        return record_view;
    }

    public void setRecord_view(String record_view) {
        this.record_view = record_view;
    }

    public String getSeparate_js() {
        return separate_js;
    }

    public void setSeparate_js(String separate_js) {
        this.separate_js = separate_js;
    }

    public String getContinuous_view() {
        return continuous_view;
    }

    public void setContinuous_view(String continuous_view) {
        this.continuous_view = continuous_view;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getArg() {
        return arg;
    }

    public void setArg(String arg) {
        this.arg = arg;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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
