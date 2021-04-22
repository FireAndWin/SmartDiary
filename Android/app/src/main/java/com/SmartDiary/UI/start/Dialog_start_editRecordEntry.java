package com.SmartDiary.UI.start;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;

import com.SmartDiary.MainActivity;
import com.SmartDiary.R;
import com.SmartDiary.pojo.CellEntry;
import com.SmartDiary.pojo.RecordEntry;
import com.SmartDiary.service.pojoService.CellEntryService;
import com.SmartDiary.service.pojoService.RecordEntryService;

import java.util.zip.Inflater;

import static android.content.ContentValues.TAG;

public class Dialog_start_editRecordEntry {

    AlertDialog dialog;
    View view;
    RecordEntry entry;
    On_RecordEntry_Edit_Listener listener;

    //---一些ui控件
    EditText  editText_editRecordEntry_name;
    EditText  editText_editRecordEntry_info;
    ImageView imageView_editRecordEntry_info;
    WebView   webView_editRecordEntry_format;

    public Dialog_start_editRecordEntry(Context context, RecordEntry entry,On_RecordEntry_Edit_Listener listener) {
        this.view= View.inflate(context, R.layout.dialog__edit__record_entry,null);
        this.entry=entry;
        this.listener=listener;

        //各种findViewByid
        findView();
        //初始化控件的值
        initViewValue();
        /*初始化那个webView,就是edit_view
        一般设置webView都会写个adapter,但是这个比较简单,
        所以不写了
        */
        init_editView();

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);
        dialog=builder.create();
        dialog.show();


        // 将对话框的大小按屏幕大小的百分比设置
        MainActivity mainActivity=(MainActivity)context;
        WindowManager windowManager = mainActivity.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = (int)(display.getWidth()); //设置宽度
        lp.height=(int)(display.getHeight());
        dialog.getWindow().setAttributes(lp);
        //当该对话框被关闭时:
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener(){
            @Override
            public void onCancel(DialogInterface dialog) {
                on_dialog_close();
            }
        });
    }

    //当对话框被关闭时调用该方法:
    private void on_dialog_close() {
        dialog.dismiss();
        //如果记录项有editView,那就从WebView获取到格式json,并放到entry中
        String edit_view=entry.getEdit_view();
        if( edit_view!=null && edit_view!=""){
            //先调用editview的getJSFormat()获取格式字符串
            // 1:首先调用js的方法获取记录值;
            webView_editRecordEntry_format.evaluateJavascript("javascript:getJSFormat()", new ValueCallback<String>() {
                @Override
                public void onReceiveValue(String format) {
                    //2:然后更新entry
                    entry.setFormat(format);
                    Log.d(TAG, "onReceiveValue: "+"已经更新格式信息,是:"+entry.getName()+format);
                }
            });
        }

        //3.然后从控件中获取值更新到entry中
        update_entry_from_view();

        //4.最后回调listener的借口
        listener.edit_recordEntry_done(entry);
    }

    private void update_entry_from_view() {
        entry.setName(editText_editRecordEntry_name.getText().toString());
        entry.setInfo(editText_editRecordEntry_info.getText().toString());
        //  TODO:还要做更新icon
    }

    private void findView() {
         editText_editRecordEntry_name=view.findViewById(R.id.editText_editRecordEntry_name);
         editText_editRecordEntry_info=view.findViewById(R.id.editText_editRecordEntry_info);
        imageView_editRecordEntry_info=view.findViewById(R.id.imageView_editRecordEntry_info);
        webView_editRecordEntry_format=view.findViewById(R.id.webView_editRecordEntry_format);
    }

    private void initViewValue() {
        editText_editRecordEntry_name.setText(entry.getName());
        editText_editRecordEntry_info.setText(entry.getInfo());
        //TODO:还要获取记录项的icon,显示到imageview上,还要加选择图片并编码的功能
    }

    private void init_editView() {
        String edit_view=entry.getEdit_view();
        //如果该记录项有editView再进行加载,否则不加载,并且把webView隐藏了.
        if(edit_view==""&& edit_view==null){
            webView_editRecordEntry_format.setVisibility(View.GONE);
            view.findViewById(R.id.textView_editRecordEntry_format).setVisibility(View.GONE);
        }
        else {
            webView_editRecordEntry_format.getSettings().setDefaultTextEncodingName("utf-8") ;
            webView_editRecordEntry_format.getSettings().setJavaScriptEnabled(true);
            webView_editRecordEntry_format.addJavascriptInterface(this,"androidObject");
            webView_editRecordEntry_format.loadDataWithBaseURL(null, entry.getEdit_view(), "text/html", "utf-8", null);
        }
    }

    //提供给js的借口,把格式信息给了js
    @JavascriptInterface
    public String getAndroidFormat(){
        return entry.getFormat();
    }

}
