package com.SmartDiary.UI.record;

import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebView;

import com.SmartDiary.Utils.TimeUtilsMy;
import com.SmartDiary.Utils.WebViewUtils.MyStringUtils;
import com.SmartDiary.pojo.CellEntry;
import com.SmartDiary.pojo.RecordEntry;
import com.SmartDiary.service.pojoService.CellEntryService;
import com.SmartDiary.service.pojoService.RecordEntryService;
import com.alibaba.fastjson.JSON;

import static android.content.ContentValues.TAG;

/*
* 因为放recordView的webView加载html,添加数据接口,事件都是类似的,
* 所以封装了一个类,用来给webView做各种重复性的和webview相关的工作,
* 比如设置webview,
* 添加数据接口,
* 加载基本页面.*/
public class Adapter4webView_record_recordView {

    WebView record_view;
    RecordEntry entry;
    long record_time;

    public Adapter4webView_record_recordView(WebView record_view,String recordEntry_id,long record_time) {
        this.record_view = record_view;
        entry= RecordEntryService.newInstance().get_recordEntry_byId(recordEntry_id);
        this.record_time=record_time;

        //基本的webView的设置:
        //record_view.getSettings().setDefaultTextEncodingName("utf-8") ;
        record_view.getSettings().setJavaScriptEnabled(true);
        record_view.addJavascriptInterface(this,"androidObject");

        load_basicHtml();
    }

    private void load_basicHtml() {
        record_view.loadDataWithBaseURL(null, entry.getRecord_view(), "text/html", "utf-8", null);
        //record_view.loadUrl("file:///android_asset/template/multiNumber/recordView.html");
    }


    /*
    * 从js获取数据,
    * 并记录到数据库中,
    * 调用时间由本类的所有者决定(可能是Fragment,可能是Dialog)*/
    public void do_record_dataBase(){
        /*
        * 首先调用js的方法获取记录值;
        * 然后更新数据储存表,
        * 然后更新最后记录时间.*/

        // 1:首先调用js的方法获取记录值;
        record_view.evaluateJavascript("javascript:getJSRecordView()", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String value) {
               //2:然后更新数据储存表,
                CellEntryService.newInstance().update_cellEntry(entry.getId(),new CellEntry(record_time,value));

                //3.然后更新最后记录时间
                entry.setLatest(System.currentTimeMillis());
                RecordEntryService.newInstance().update_recordEntry(entry);

                Log.d(TAG, "onReceiveValue: "+"wahahahah已经放到数据库了,日期:"+ TimeUtilsMy.long_2_MonthDay(record_time)+" 具体:" +entry.getName()+value);
            }
        });
    };

    //================提供给js的对象======================
    @JavascriptInterface
    public String getAndroidFormat(){
        String res= MyStringUtils.JSON_2_String(entry.getFormat());
        return res;
    }

    @JavascriptInterface
    public String getAndroidRecordValue(){
        String value= CellEntryService.newInstance().get_recordValue_byID_Date(entry.getId(),record_time);
        String res= MyStringUtils.JSON_2_String(value);
        return res;
//        Log.d(TAG, "getAndroidRecordValue: "+"1142:swswsw getAndroidRecordValue"+entry.getName()+value);
//        return value;
    }
}
