package com.SmartDiary.UI.record;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.webkit.WebView;

import com.SmartDiary.R;
import com.SmartDiary.UI.start.On_RecordEntry_Edit_Listener;
import com.SmartDiary.pojo.CellEntry;
import com.SmartDiary.pojo.RecordEntry;

public class Dialog_record_recordView {

    AlertDialog dialog;
    View view;
    Adapter4webView_record_recordView adapter;
    public Dialog_record_recordView(Context context, String recordEntry_id,long date) {
        View view= View.inflate(context, R.layout.dialog_recordview,null);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        WebView recordView=view.findViewById(R.id.webView_dialog_recordView);
        adapter=new Adapter4webView_record_recordView(recordView,recordEntry_id,date);
        builder.setView(view);
        dialog=builder.create();
        dialog.show();

        dialog.setOnCancelListener(new DialogInterface.OnCancelListener(){
           @Override
           public void onCancel(DialogInterface dialog) {
               dialog.dismiss();
               adapter.do_record_dataBase();
           }
       });
    }
}
