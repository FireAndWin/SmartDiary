package com.SmartDiary.UI.check;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.SmartDiary.R;
import com.SmartDiary.pojo.DayEntry;
import com.SmartDiary.pojo.RecordEntry;
import com.SmartDiary.pojo.RecordTemplate;
import com.SmartDiary.service.pojoService.RecordEntryService;
import com.SmartDiary.service.pojoService.RecordTemplateService;
import com.alibaba.fastjson.JSONArray;

import java.util.ArrayList;
import java.util.List;

import static com.SmartDiary.UI.record.Adapter_record_tableView.TAG;

//这个类的功能就是根据dayEntry返回一个展示改天数据的webView
public class Adapter_check_dayEntry2webView {
    public static final String TAG = "dayEntry2webView";
    DayEntry dayEntry;
    /*构造函数:
    * 1.加载内部变量dayEntry;
    * 2.给webView加载最基本的html;
    * 3.给webView添加JavaScriptInterface;
    * 4.给webView执行动态js注入;*/

    public Adapter_check_dayEntry2webView(View view, DayEntry dayEntry) {

        this.dayEntry=dayEntry;

        WebView webView_check_dayEntryDisplay=view.findViewById(R.id.webView_check_dayEntryDisplay);
        webView_check_dayEntryDisplay.getSettings().setDefaultTextEncodingName("utf-8") ;
        webView_check_dayEntryDisplay.getSettings().setJavaScriptEnabled(true);
        webView_check_dayEntryDisplay.addJavascriptInterface(this,"androidObject");

        load_basicHTML(webView_check_dayEntryDisplay);
        load_separate_js(webView_check_dayEntryDisplay);
    }

    //给webView加载最基本的html;
    private void load_basicHTML(WebView webView_check_dayEntryDisplay) {
        String table_view="<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=<device-width>, initial-scale=1.0\">\n" +
                "    <title>Document</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div id=\"date\">在这里显示时间</div>\n" +
                "    <div id=\"dayEntry_table\"></div>\n" +
                "    <script>\n" +
                "        //第一步要获得所有数据\n" +
                "            //先获取所有要显示的记录项\n" +
                "        let JsonRecordEntryList=window.androidObject.getAndroidRecordEntryList();\n" +
                "        let recordEntryList=JSON.parse(JsonRecordEntryList);\n" +
                "            //然后获得日期\n" +
                "        let date=window.androidObject.getAndroidDate();\n" +
                "\n" +
                "\n" +
                "        //第二部准备好给separate_js准备的函数\n" +
                "\n" +
                "        //第三步加载数据\n" +
                "        let div_dayEntry_table=document.getElementById(\"dayEntry_table\");\n" +
                "        for(i in recordEntryList){\n" +
                "            let recordEntry=recordEntryList[i];\n" +
                "\n" +
                "            //放数据的整个格子\n" +
                "            let div_cell=document.createElement(\"div\");\n" +
                "\n" +
                "\n" +
                "            //记录项名称的地方\n" +
                "            let div_name=document.createElement(\"div\");\n" +
                "            div_name.innerHTML=recordEntry.name;\n" +
                "            div_name.style.backgroundColor=\"#03A9F4\";\n" +
                "\n" +
                "            //放记录项数据的地方\n" +
                "            let div_value=document.createElement(\"div\");\n" +
                "            div_value.setAttribute(\"template_id\",recordEntry.template_id);\n" +
                "            let record_value=window.androidObject.getAndroidRecordValue_byId(recordEntry.id);\n" +
                "            div_value.setAttribute(\"value\",record_value);\n" +
                "            div_value.innerText=recordEntry.template_id+record_value;\n" +
                "\n" +
                "\n" +
                "            //加入到父元素中\n" +
                "            div_dayEntry_table.appendChild(div_cell);\n" +
                "            div_cell.appendChild(div_name);\n" +
                "            div_cell.appendChild(div_value);\n" +
                "        }\n" +
                "\n" +
                "        //第四步修改时间显示\n" +
                "        let div_date=document.getElementById(\"date\");\n" +
                "        let long_date=window.androidObject.getAndroidDate();\n" +
                "        div_date.innerHTML= new Date(long_date).toLocaleString();\n" +
                "    </script>\n" +
                "    \n" +
                "</body>\n" +
                "</html>";

        webView_check_dayEntryDisplay.loadDataWithBaseURL(null, table_view, "text/html", "utf-8", null);

    }

    //给webView执行动态js注入;
    private void load_separate_js(WebView webView_check_dayEntryDisplay) {
        List<RecordEntry> recordEntryList=RecordEntryService.newInstance().getAll();

        webView_check_dayEntryDisplay.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

                //遍历记录项,获取分别渲染的js代码,然后动态注入
                for(RecordEntry recordEntry:recordEntryList){
                    RecordTemplate template= RecordTemplateService.newInstance().getObject_byID(recordEntry.getTemplate_id());
                    String separate_js=template.getSeparate_js();

                    webView_check_dayEntryDisplay.evaluateJavascript(separate_js, new ValueCallback<String>() {
                        @Override public void onReceiveValue(String value) {//js与native交互的回调函数
                        }
                    });
                }
            }
        });
    }

    @JavascriptInterface
    public  String getAndroidRecordEntryList(){
        RecordEntryService recordEntryService= RecordEntryService.newInstance();
        List<RecordEntry> recordEntryList=recordEntryService.getAll();
        String resultJson= JSONArray.toJSONString(recordEntryList);
        Log.d(TAG, "getAndroidRecordEntryList: "+"857: "+resultJson);
        return resultJson;
    };

    @JavascriptInterface
    public String getAndroidRecordValue_byId(String recordEntry_id){
        return dayEntry.getMap().get(recordEntry_id);
    }

    @JavascriptInterface
    public long getAndroidDate(){
        return dayEntry.getDate();
    }
}
