package com.SmartDiary.service.pojoService;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class UserDataBaseSQLHelper extends SQLiteOpenHelper {

    //还是单例设计模式
    private static UserDataBaseSQLHelper dbHelper;
    public static void init(Context context){
        dbHelper=new UserDataBaseSQLHelper(context,"user.db",null,1);
    }
    public static UserDataBaseSQLHelper newInstance(){
        return dbHelper;
    }



    private static final String TAG = "RecordEntryService";
    //基本的建表语句
    public static final String Create_MyRecordEntry_table="" +
            "create table MyRecordEntry(" +
            "_id integer primary key autoincrement," +
            "id text ," +
            "name text," +
            "info text," +
            "icon blob," +
            "latest integer," +
            "analysis_result text," +
            "" +
            "template_id text," +
            "format text," +
            "edit_view text," +
            "record_view text," +
            "separate_js text," +
            "continuous_view text," +
            "" +
            "status integer," +
            "locked integer," +
            "arg text)";


    public UserDataBaseSQLHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        Log.d(TAG, "onCreate: "+"建表语句 RecordEntrySQLHelper ");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate: "+"建表语句执行开始");
        db.execSQL(Create_MyRecordEntry_table);
        Log.d(TAG, "onCreate: "+"建表语句执行完毕");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
