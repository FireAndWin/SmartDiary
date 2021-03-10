package com.SmartDiary.UI.record;

import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.SmartDiary.pojo.CellEntry;
import com.SmartDiary.pojo.RecordEntry;
import com.SmartDiary.pojo.RecordTemplate;
import com.SmartDiary.service.pojoService.RecordEntryService;
import com.SmartDiary.service.pojoService.RecordTemplateService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/*记录界面的webView_record_table,
* 需要选择向它加载的数据,
* 就是一个转换器,
* 把安卓提供的数据给了WebView*/
public class Adapter_record_tableView {
    public static final String TAG = "Adapter_recordtableView";
    WebView webView_record_table;
    String recordEntryID;
    RecordEntryService recordEntryService;
    RecordTemplateService recordTemplateService;
    long start_date;
    int day_count;

    /*初始化方法,把webView要做的事情都初始化好:
    * 0:初始化相关成员变量
    * 1:添加和安卓交互的对象;
    * 2.设置webView的基本html内容;
    * 3.webView的一些相关设置;
    * 4.从模板获取到动态注入的内容;
    * */
    public Adapter_record_tableView(
            WebView webView_record_table,
            String recordEntryID,
            RecordEntryService recordEntryService,
            RecordTemplateService recordTemplateService) {
        this.webView_record_table = webView_record_table;
        this.recordEntryID=recordEntryID;
        this.recordEntryService=recordEntryService;
        this.recordTemplateService=recordTemplateService;

        //webView的一些设置
        webView_record_table.getSettings().setDefaultTextEncodingName("utf-8") ;
        webView_record_table.getSettings().setJavaScriptEnabled(true);
        webView_record_table.addJavascriptInterface(this,"androidObject");

        //向WebView中添加基本的html代码
        load_basicHTML(webView_record_table);
        //向webView中动态注入js进行单个记录值的渲染绘制
        load_separate_js(webView_record_table);
    }

    private void load_basicHTML(WebView webView_record_table) {
        String table_view="<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Document</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div id=\"android_data\">安卓传过来的数据</div>\n" +
                "    <div id=\"cellEntry_table\">表格div</div>\n" +
                "    <div id=\"my_interface\"></div>\n" +
                "    <script>\n" +
                "\n" +
                "        //第一步要获得所有数据\n" +
                "            //获取该页面要展示的记录项Json:\n" +
                "        let recordEntryJson=window.androidObject.getAndroidRecordEntry();\n" +
                "        //recordEntryJson=\"{\\\"id\\\":\\\"idString\\\",\\\"name\\\":\\\"记录项名称,比如学习情况\\\",\\\"template_id\\\":\\\"模板idString\\\",\\\"analysis_result\\\":\\\"分析结果String\\\",\\\"format\\\":\\\"格式String\\\"}\";\n" +
                "        let recordEntry=JSON.parse(recordEntryJson);\n" +
                "        //获取该页面要展示的具体数据:\n" +
                "        let cellEntryListJson=window.androidObject.getAndroidCellEntryList();\n" +
                "        //cellEntryListJson=\"[{\\\"date\\\":1111100,\\\"value\\\":\\\"睡得很早\\\"},{\\\"date\\\":1111100,\\\"value\\\":\\\"睡得很早\\\"},{\\\"date\\\":1111100,\\\"value\\\":\\\"睡得很早\\\"},{\\\"date\\\":1111100,\\\"value\\\":\\\"睡得很早\\\"},{\\\"date\\\":1111100,\\\"value\\\":\\\"睡得很早\\\"}]\";\n" +
                "        let cellEntryList=JSON.parse(cellEntryListJson);\n" +
                "\n" +
                "\n" +
                "        //第二部准备好给separate_js准备的函数\n" +
                "        window.getFormat=function(template_id){\n" +
                "            return recordEntry.format;\n" +
                "        };\n" +
                "\t    window.getAnalysisResult=function(template_id){\n" +
                "            return recordEntry.analysis_result;\n" +
                "        };\n" +
                "        let my_interface=document.getElementById(\"my_interface\");\n" +
                "        my_interface.innerHTML=\"格式json为:\"+window.getFormat()+\"\\n\"+\"分析结果为:\"+window.getAnalysisResult();\n" +
                "\n" +
                "\n" +
                "        //第三步加载数据到dom元素中\n" +
                "        let div_android_data=document.getElementById(\"android_data\");\n" +
                "        div_android_data.innerHTML=recordEntryJson+\"\\n\"+cellEntryListJson;\n" +
                "        let div_table=document.getElementById(\"cellEntry_table\");\n" +
                "        for (i in cellEntryList){\n" +
                "            cellEntry=cellEntryList[i];\n" +
                "\n" +
                "            //放数据的整个格子\n" +
                "            let div_cell=document.createElement(\"div\");\n" +
                "\n" +
                "            //放日期的地方\n" +
                "            let div_date=document.createElement(\"div\");\n" +
                "            div_date.textContent= new Date(cellEntry.date).toLocaleString();\n" +
                "            div_date.style.backgroundColor=\"#03A9F4\";\n" +
                "\n" +
                "            //放数据的地方:\n" +
                "            let div_value=document.createElement(\"div\");\n" +
                "            div_value.setAttribute(\"template_id\",recordEntry.template_id);\n" +
                "            div_value.setAttribute(\"value\",cellEntry.value);\n" +
                "            div_value.innerText=recordEntry.template_id+cellEntry.value;\n" +
                "\n" +
                "            div_cell.appendChild(div_date);\n" +
                "            //div_cell.appendChild(\"<br>\");\n" +
                "            div_cell.appendChild(div_value);\n" +
                "\n" +
                "            div_table.appendChild(div_cell);\n" +
                "        } \n" +
                "\n" +
                "    </script>\n" +
                "</body>\n" +
                "</html>";

        webView_record_table.loadDataWithBaseURL(null, table_view, "text/html", "utf-8", null);

    }

    private void load_separate_js(WebView webView_record_table) {
        //获取要动态注入的separate_js
        /*这个是正规写法
        RecordEntry entry=recordEntryService.getObject_ById(recordEntryID);
        RecordTemplate template=recordTemplateService.getObject_byID(entry.getTemplate_id());
        String separate_js=template.getSeparate_js();
        String separate_js=template.getSeparate_js();*/
        String separate_js="let div_1=document.getElementById(\"div1\");\n" +
                "            div_1.innerHTML=\"我被改了\";";

        webView_record_table.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
//                webView_record_table.addJavascriptInterface(Adapter_record_tableView.this,"androidObject");
//                webView_record_table.loadDataWithBaseURL(null, table_view, "text/html", "utf-8", null);
                webView_record_table.evaluateJavascript(separate_js, new ValueCallback<String>() {
                    @Override public void onReceiveValue(String value) {//js与native交互的回调函数
                    }
                });
            }
        });
    }

    /*设置webView要显示的数据时段,也就是起止日期,还有就是时段*/
    public void setPeriod(long start_date,int day_count){
        this.start_date=start_date;
        this.day_count=day_count;
    }

    //===============以下是提供给js的借口了==========================
    //获取要展示的RecordEntry的相关信息
    @JavascriptInterface
    public String getAndroidRecordEntry(){


        //这里只是暂时代码,主要是过一下json的生成过程
        RecordEntry test_entry=new RecordEntry("测试用记录项","这个是测试用的","test_id");
        test_entry.setFormat("format_test,json");
        test_entry.setAnalysis_result("analysis_result,used for test");
        test_entry.setTemplate_id("template_id,for test use");

        String resultJson= JSON.toJSONString(test_entry);
        Log.d(TAG, "getAndroidRecordEntry: "+"202139,被调用了");
        Log.d(TAG, "getAndroidRecordEntry: "+resultJson);
        return resultJson;
        //return "{\"id\":\"idString\",\"name\":\"记录项名称,比如学习情况\",\"template_id\":\"模板idString\",\"analysis_result\":\"分析结果String\",\"format\":\"格式String\"}";
    }
    //获取要显示的很多天的数据.
    @JavascriptInterface
    public String getAndroidCellEntryList(){

        //这段代码也是暂时的,只是为了测试json的转换流程
        List<CellEntry> cellEntryList=new ArrayList<>();
        long now=System.currentTimeMillis();
        cellEntryList.add(new CellEntry(now,"记录值1"));
        cellEntryList.add(new CellEntry(now,"记录值2"));
        cellEntryList.add(new CellEntry(now,"记录值3"));
        cellEntryList.add(new CellEntry(now,"记录值4"));
        String resultJson= JSONArray.toJSONString(cellEntryList);
        Log.d(TAG, "getAndroidRecordEntry: "+"202139,被调用了");
        Log.d(TAG, "getAndroidRecordEntry: "+resultJson);
        return resultJson;
        //return "[{\"date\":1111100,\"value\":\"睡得很早\"},{\"date\":1111100,\"value\":\"睡得很早\"},{\"date\":1111100,\"value\":\"睡得很早\"},{\"date\":1111100,\"value\":\"睡得很早\"},{\"date\":1111100,\"value\":\"睡得很早\"}]";
    }
}
