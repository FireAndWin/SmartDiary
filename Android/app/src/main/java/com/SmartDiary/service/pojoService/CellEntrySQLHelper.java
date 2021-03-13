package com.SmartDiary.service.pojoService;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CellEntrySQLHelper extends SQLiteOpenHelper {
    //先创建一个基本的管理的表
    public static final String Create_Manager_table="" +
            "create table Manager (" +
            "id integer primary key autoincrement, " +
            "recordEntry_id text, " +
            "usable integer" +
            ")";


    public CellEntrySQLHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Create_Manager_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
