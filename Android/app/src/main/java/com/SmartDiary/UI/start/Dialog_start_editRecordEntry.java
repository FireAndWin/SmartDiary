package com.SmartDiary.UI.start;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;

import com.SmartDiary.MainActivity;
import com.SmartDiary.R;
import com.SmartDiary.pojo.RecordEntry;

import java.util.zip.Inflater;

public class Dialog_start_editRecordEntry {

    AlertDialog dialog;
    View view;
    On_RecordEntry_Edit_Listener listener;
    public Dialog_start_editRecordEntry(Context context, RecordEntry entry,On_RecordEntry_Edit_Listener listener) {
        View view= View.inflate(context, R.layout.dialog__edit__record_entry,null);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setView(view);
        dialog=builder.create();
        dialog.show();
    }
}
