package com.SmartDiary.service.pojoService;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.SmartDiary.pojo.CellEntry;

public class CellEntryService {
    private static final String TAG = "pojoService";


    //数据库版本,很烦人的东西
    int version=1;
    //数据库名称
    private String dataBase_name="cellEntry.db";
    //SQLiteOpenHelper
    private CellEntrySQLHelper dbHelper;
    Context context;

    public CellEntryService(Context context) {
        this.context = context;
        dbHelper=new CellEntrySQLHelper(context,dataBase_name,null,version);
    }


    /*
    * 当添加新的记录项时,记得调用此函数,
    * 会在本数据库中添加新的表,
    * 在管理表中添加一行,
    * 添加的表名称就是"Table"+id*/
    public void add_recordEntry(String recordEntry_id){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        if(db.isOpen()) {

            //首先添加一张表:
            String add_recordEntry_table = "create table Table" + recordEntry_id + " (" +
                    "id integer primary key autoincrement," +
                    "date integer, " +
                    "value text " +
                    ")";
            db.execSQL(add_recordEntry_table);

            //然后在管理表中添加一个行
            db.execSQL("insert into Manager (recordEntry_id,usable) values (" +
                    "'" +recordEntry_id+"'"+","+
                    1+""+
                    ")");
        }
        db.close();
    }

    /*
    * 更新(插入)操作
    * 这里update和insert的功能其实很类似,都是修改某一天的值,
    * 当数据发生改变时只要调用update借口就好了
    * */
    public void update_cellEntry(String recordEntry_id,CellEntry cellEntry){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        if(db.isOpen()){
            /*
            先把数据封装好,
            然后找有没有那天的数据,
            如果有:就update,
            如果没有:就insert.*/

            //先封装数据
            long date =cellEntry.getDate();
            ContentValues values=new ContentValues();
            values.put("date",date);
            values.put("value",cellEntry.getValue());

            //然后查询:
            Cursor cursor=db.query(
                    "Table"+recordEntry_id,
                    new String[]{"date"},
                    "date=?",
                    new String[]{String.valueOf(date)},
                    null,null,null
            );

            //根据查询结果选择哪种方法
                //如果没有找到数据,就进行insert
            if(cursor.getCount()==0){
                db.insert("Table"+recordEntry_id,null,values);
            }
                //如果找到数据了,就进行update
            else{
                Log.d(TAG, "update_cellEntry: "+"cursor的getCount()方法返回值为1");
                Log.d(TAG, "update_cellEntry: "+"cursor的第0列为:"+cursor.getColumnName(0));
                Log.d(TAG, "update_cellEntry: "+"cursor的列数为:"+cursor.getColumnCount());
                db.update("Table"+recordEntry_id,
                        values,
                        "date=?",
                        new String[]{String.valueOf(date)});

            }
        }
    }

    /*
    * 查询操作:
    * 根据date和recordEntry_id返回CellEntry,
    * 如果有这天这个记录项的数据,就返回,
    * 如果没有这天这个记录项的数据,就返回null
     */
    public CellEntry get_cellEntry(String recordEntry_id, long date){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        if(db.isOpen()){
            Cursor cursor=db.rawQuery("select * from Table"+recordEntry_id+" where date=?",new String[]{String.valueOf(date)});
            if(cursor.getColumnCount()>0){
                if(cursor.moveToFirst()){
                    String value=cursor.getString(cursor.getColumnIndex("value"));
                    Log.d(TAG, "get_cellEntry: "+"cursor的第2列值为:"+value);
                    CellEntry cellEntry = new CellEntry();
                    cellEntry.setDate(date);
                    cellEntry.setValue(value);
                    return cellEntry;
                }
            }
        }
        db.close();
        return null;
    }

    /*
    * 删除操作:
    * 注意删除的不是cellEntry,而是recordEntry,
    * 调用此方法会直接把该记录项的所有数据全部删除,
    * 慎用,删除和放入回收站是两回事*/
    public void delete_recordEntry(String recordEntry_id){

        /*
        * 第一步是在管理表中删除该行,
        * 第二部是删除该表;*/

        SQLiteDatabase db=dbHelper.getWritableDatabase();
        if(db.isOpen()){
            //第一步,删除管理表中的该项
            db.delete("Manager","recordEntry_id=?",new String[]{recordEntry_id});

            //第二步,删除这个表
            db.execSQL("drop table if exists Table"+recordEntry_id);
        }
        db.close();
    }
}
