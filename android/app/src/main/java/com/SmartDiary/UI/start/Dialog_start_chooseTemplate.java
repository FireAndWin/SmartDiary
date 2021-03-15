package com.SmartDiary.UI.start;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.SmartDiary.R;
import com.SmartDiary.pojo.RecordEntry;
import com.SmartDiary.pojo.RecordTemplate;
import com.SmartDiary.service.pojoService.RecordEntryService;

//选择模板界面
public class Dialog_start_chooseTemplate {

    //要创建编辑记录项对话框需要的参数,自己不怎么用;
    On_RecordEntry_Edit_Listener listener;
    Context context;

    AlertDialog dialog;
    View view;

    public Dialog_start_chooseTemplate(Context context, On_RecordEntry_Edit_Listener listener) {

        this.context=context;
        this.listener=listener;

        View view= View.inflate(context, R.layout.dialog__choose_template,null);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);
        dialog=builder.create();
        dialog.show();

        test_bindView(view);
    }


    private void test_bindView(View view) {
        view.findViewById(R.id.test_txt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecordEntry entry= RecordEntryService.newInstance().get_recordEntry_byId("txt001");
                go_create_RecordEntry(entry);
            }
        });
        view.findViewById(R.id.test_numbers).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecordEntry entry= RecordEntryService.newInstance().get_recordEntry_byId("number001");
                go_create_RecordEntry(entry);
            }
        });
        view.findViewById(R.id.test_choose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecordEntry entry= RecordEntryService.newInstance().get_recordEntry_byId("choice001");
                go_create_RecordEntry(entry);
            }
        });
        view.findViewById(R.id.test_singleNumber).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecordEntry entry= RecordEntryService.newInstance().get_recordEntry_byId("txt001");
                go_create_RecordEntry(entry);
            }
        });
    }

    //选择好模板后,调用此方法去创建具体的记录项
    public void go_create_RecordEntry(RecordEntry entry){
        dialog.dismiss();
        Dialog_start_editRecordEntry dialog_start_editRecordEntry=new Dialog_start_editRecordEntry(context,entry,listener);
    }
}
