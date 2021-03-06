package com.SmartDiary.UI.start;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.SmartDiary.R;
import com.SmartDiary.pojo.RecordEntry;
import com.SmartDiary.pojo.RecordTemplate;

//选择模板界面
public class Dialog_start_chooseTemplate {

    //要创建编辑记录项对话框需要的参数,自己不怎么用;
    On_RecordEntry_Edit_Listener listener;
    Context context;

    Button btn_test_chooseDone;
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

        initview(view);
        bindView(view);
    }

    private void initview(View view) {
        btn_test_chooseDone=view.findViewById(R.id.btn_test_chooseDone);
    }

    private void bindView(View view) {
        btn_test_chooseDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                go_create_RecordEntry(null);
            }
        });
    }

    //选择好模板后,调用此方法去创建具体的记录项
    public void go_create_RecordEntry(RecordTemplate template){
        dialog.dismiss();
        RecordEntry entry=new RecordEntry("娱乐项目","记录今天玩了个啥","44");
        Dialog_start_editRecordEntry dialog_start_editRecordEntry=new Dialog_start_editRecordEntry(context,entry,listener);
    }
}
