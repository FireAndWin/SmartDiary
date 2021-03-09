package com.SmartDiary.Utils.WebViewUtils;

import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.SmartDiary.pojo.CellEntry;

import java.util.List;

/**


 */

public class JsDataLoader {

    //待处理的WebView
    WebView webView;

    //实际数据
    String androidFormat;
    String androidOneCellEntry;
    String androidCellEntryList;
    String androidAnalysis;
    String androidData;


    public JsDataLoader(WebView webView) {
        this.webView=webView;
        webView.addJavascriptInterface(this,"androidBridge");
    }

    /*向WebView中加载格式字符串*/
    public void add_androidFormat(String formatString){}

    /*向WebView中加载一个记录项一天的记录*/
    public void add_androidOneCellEntry(CellEntry cell){}

    /*向WebView中添加一个记录项的的多天的记录值*/
    public void add_androidCellEntryList(List<CellEntry> cellEntryList){}

    /*向WebView中添加分析结果*/
    public void add_androidAnalysis(String analysis){}

    /*向WebView中添加多个记录项的多个值,这个参数还没想好是什么格式
    * 你可以自己改*/
    public void add_androidData(String data){}


    @JavascriptInterface
    public String getAndroidFormat() {
        return androidFormat;
    }

    @JavascriptInterface
    public String getAndroidOneCellEntry() {
        return androidOneCellEntry;
    }

    @JavascriptInterface
    public String getAndroidCellEntryList() {
        return androidCellEntryList;
    }

    @JavascriptInterface
    public String getJSAnalysisResult() {
        return androidAnalysis;
    }

    @JavascriptInterface
    public String getAndroidData() {
        return androidData;
    }
}
