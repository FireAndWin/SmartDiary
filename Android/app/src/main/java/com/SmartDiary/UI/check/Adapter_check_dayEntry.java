package com.SmartDiary.UI.check;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.SmartDiary.MainActivity;
import com.SmartDiary.R;
import com.SmartDiary.UI.record.Dialog_record_recordView;
import com.SmartDiary.Utils.TimeUtilsMy;
import com.SmartDiary.pojo.DayEntry;
import com.SmartDiary.pojo.RecordEntry;
import com.SmartDiary.pojo.RecordTemplate;
import com.SmartDiary.service.pojoService.CellEntryService;
import com.SmartDiary.service.pojoService.RecordEntryService;
import com.SmartDiary.service.pojoService.RecordTemplateService;
import com.alibaba.fastjson.JSONArray;

import java.util.HashMap;
import java.util.List;

public class Adapter_check_dayEntry {

    public Context context;
    public View view;
    public int day_gap;
    public long date;
    public WebView webView_check_dayEntryDisplay;
    public DayEntry dayEntry;

    public Adapter_check_dayEntry(Context context, int day_gap) {
        this.context = context;
        this.day_gap = day_gap;
        this.date= TimeUtilsMy.get_deltaTime_long(-day_gap);

        this.view= LayoutInflater.from(context).inflate(R.layout.view_check_content_daily,null);
        dayEntry=new DayEntry();
        form_dayEntry();

        WebView webView_check_dayEntryDisplay=view.findViewById(R.id.webView_check_dayEntryDisplay);
        webView_check_dayEntryDisplay.getSettings().setDefaultTextEncodingName("utf-8") ;
        webView_check_dayEntryDisplay.getSettings().setJavaScriptEnabled(true);
        webView_check_dayEntryDisplay.addJavascriptInterface(this,"androidObject");

        load_basicHTML(webView_check_dayEntryDisplay);
        load_separate_js(webView_check_dayEntryDisplay);

        //new Adapter_check_dayEntry2webView(view,context,this.dayEntry);
    }

    public View getView() {
        return view;
    }

    //根据日期去pojoService中查询,获得dayentry
    private void form_dayEntry(){
        dayEntry.date=date;
        dayEntry.map=new HashMap<>();
        RecordEntryService recordEntryService= RecordEntryService.newInstance();
        CellEntryService cellEntryService=CellEntryService.newInstance();
        List<RecordEntry> recordEntryList=recordEntryService.get_recordEntryList_byStatus(new int[]{0,1});

        //遍历记录项,根据id和date获取记录值
        for(RecordEntry recordEntry:recordEntryList){
            String value=cellEntryService.get_recordValue_byID_Date(recordEntry.getId(),date);
            if(value==null || value==""){
                value="NaN";
            }
            dayEntry.map.put(String.valueOf(recordEntry.getId()),value);
        }
    }

    //给webView加载最基本的html;
    private void load_basicHTML(WebView webView_check_dayEntryDisplay) {
        webView_check_dayEntryDisplay.loadUrl("file:///android_asset/androidTableView/checkTableView.html");
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
        List<RecordEntry> recordEntryList=recordEntryService.get_recordEntryList_byStatus(new int[]{0,1});
        String resultJson= JSONArray.toJSONString(recordEntryList);
        //Log.d(TAG, "getAndroidRecordEntryList: "+"857: "+resultJson);
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

    //召唤recordView
    @JavascriptInterface
    public void callAndroidRecordView(String recordEntryID){

        MainActivity activity=(MainActivity)context
                ;
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new Dialog_record_recordView(context,recordEntryID,date);
            }
        });
    }
}
