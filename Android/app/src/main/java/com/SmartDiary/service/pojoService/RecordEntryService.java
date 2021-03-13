package com.SmartDiary.service.pojoService;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import com.SmartDiary.pojo.RecordEntry;

import java.util.ArrayList;
import java.util.List;

public class RecordEntryService {


    //测试时使用的只存在于内存中的数据
    List<RecordEntry> test_list;
    List<RecordEntry> recordEntryList;
    public RecordEntryService() {
        test_list=new ArrayList<>();
        test_list.add(new RecordEntry("娱乐方式","选择模板的测试","21","11"));
        test_list.add(new RecordEntry("日记","文本模板的测试项","22","12"));
        test_list.add(new RecordEntry("每日支出","数量模板的记录项","23","13"));

        dbHelper=UserDataBaseSQLHelper.newInstance();
        /*
        test_list.add(new RecordEntry("锻炼身体","年轻人要多锻炼身体","2"));
        test_list.add(new RecordEntry("体重","要好好吃饭","3"));
        test_list.add(new RecordEntry("学习情况","记录学习时间内容分布","4"));
        test_list.add(new RecordEntry("记账","花了多少钱,花在哪里","5"));
        test_list.add(new RecordEntry("娱乐app使用时长","不要老是玩手机","6"));
        test_list.add(new RecordEntry("待办事项","TODO list","7"));
        test_list.add(new RecordEntry("痘痘情况","脸上痘痘的情况","8"));
        test_list.add(new RecordEntry("雏雁计划日志","进度怎么样","9"));
        test_list.add(new RecordEntry("心情","心情怎么样","10"));*/
    }

    //=================get(读取相关)方法======================
    /*获取所有的记录项对象*/
    public List<RecordEntry> getAll(){

        return test_list;
    }
    /*根据id获取RecordEntry对象*/
    public RecordEntry getObject_ById(String id){
        for(RecordEntry recordEntry:test_list){
            if(recordEntry.getId()==id)
                return recordEntry;
        }
        return null;
    }


    //==============================实际用的方法=====================================
    private static final String TAG = "RecordEntryService";

    //单例模式
    public static RecordEntryService recordEntryService;
    public static RecordEntryService newInstance() {
        if(recordEntryService==null){
            recordEntryService=new RecordEntryService();
        }
        return recordEntryService;
    }


    //表名
    private String userRecordEntryList_table_name="MyRecordEntry";
    //SQLiteOpenHelper
    private UserDataBaseSQLHelper dbHelper;

    //真正的无参构造方法.
    //public RecordEntryService() { dbHelper=UserDataBaseSQLHelper.newInstance(); }

    /*
     * 添加一个记录项,
     * 对应于SQL的insert语句*/
    public void add_recordEntry(RecordEntry entry){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        if(db.isOpen()){
            long res=db.insert(userRecordEntryList_table_name,null,recordEntry_2_contentValues(entry));
        }
        db.close();
    }

    /*
     * 修改一个记录项,
     * 对应于SQL的update语句,
     * 去数据表中找到和这个记录项id相同的,然后覆盖*/
    public void update_recordEntry(RecordEntry entry){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        if(db.isOpen()){
            db.update(userRecordEntryList_table_name,
                    recordEntry_2_contentValues(entry),
                    "id=?",
                    new String[]{entry.getId()});
        }
        db.close();
    }

    /*
     * 获取所有记录项,
     * 可能会返回被删除的和被停用的*/
    public List<RecordEntry> getAll_recordEntry(){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        List<RecordEntry> entryList=new ArrayList<>();
        if(db.isOpen()){
            Cursor cursor=db.rawQuery("select * from "+userRecordEntryList_table_name,new String[]{},null);
            if(cursor.getColumnCount()>0){
                cursor.moveToFirst();
                do{
                    RecordEntry recordEntry=cursor_2_recordEntry(cursor);
                    entryList.add(recordEntry);
                }
                while (cursor.moveToNext());
            }
        }
        db.close();
        return entryList;
    }

    /*
     * 根据status值获取记录项的值*/
    public List<RecordEntry> get_recordEntryList_byStatus(int status){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        List<RecordEntry> entryList=new ArrayList<>();
        if(db.isOpen()){
            Cursor cursor=db.rawQuery("select * from "+userRecordEntryList_table_name+" where status = ?",new String[]{String.valueOf(status)},null);
            if(cursor.getColumnCount()>0){
                cursor.moveToFirst();
                do{
                    RecordEntry recordEntry=cursor_2_recordEntry(cursor);
                    entryList.add(recordEntry);
                }
                while (cursor.moveToNext());
            }
        }
        db.close();
        return entryList;
    }

    /*
     * 根据多个status值获取记录项的值*/
    public List<RecordEntry> get_recordEntryList_byStatus(int[] status){
        List<RecordEntry> entryList=new ArrayList<>();
        for(int i=0;i<status.length;i++){
            List<RecordEntry> singleStatus=get_recordEntryList_byStatus(status[i]);
            entryList.addAll(singleStatus);
        }
        return entryList;
    }

    /*
     * 查询单个记录项
     * 根据id获取记录项*/
    public RecordEntry get_recordEntry_byId(String recordEntry_id){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        if(db.isOpen()){
            Cursor cursor=db.rawQuery("select * from "+userRecordEntryList_table_name+" where id=?",new String[]{recordEntry_id},null);
            if(cursor.getCount()>0){
                cursor.moveToFirst();
                return cursor_2_recordEntry(cursor);
            }
        }
        db.close();
        return null;
    }

    /*
     * 删除MyRecordEntry表中的单个记录项,
     * 根据id,
     * 慎用*/
    public void delete_recordEntry_byId(String recordEntry_id){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        if(db.isOpen()){
            db.delete(userRecordEntryList_table_name,"id=?",new String[]{recordEntry_id});
        }
        db.close();
    }

    //---------一些辅助方法----------------
    public ContentValues recordEntry_2_contentValues(RecordEntry e){
        ContentValues values=new ContentValues();

        values.put("id",e.id);
        values.put("name",e.name);
        values.put("info",e.info);
        values.put("icon",e.icon);
        values.put("latest",e.latest);
        values.put("analysis_result",e.analysis_result);
        values.put("template_id",e.template_id);
        values.put("format",e.format);
        values.put("edit_view",e.edit_view);
        values.put("record_view",e.record_view);
        values.put("separate_js",e.separate_js);
        values.put("continuous_view",e.continuous_view);
        values.put("status",e.status);
        values.put("locked",e.locked);
        values.put("arg",e.arg);

        return values;
    }

    public RecordEntry cursor_2_recordEntry(Cursor c){
        RecordEntry e=new RecordEntry();
        e.id =c.getString(c.getColumnIndex("id"));
        e.name =c.getString(c.getColumnIndex("name"));
        e.info =c.getString(c.getColumnIndex("info"));
        e.icon =c.getString(c.getColumnIndex("icon"));
        e.latest =c.getLong(c.getColumnIndex("latest"));
        e.analysis_result =c.getString(c.getColumnIndex("analysis_result"));
        e.template_id =c.getString(c.getColumnIndex("template_id"));
        e.format =c.getString(c.getColumnIndex("format"));
        e.edit_view =c.getString(c.getColumnIndex("edit_view"));
        e.record_view =c.getString(c.getColumnIndex("record_view"));
        e.separate_js =c.getString(c.getColumnIndex("separate_js"));
        e.continuous_view =c.getString(c.getColumnIndex("continuous_view"));
        e.status =c.getInt(c.getColumnIndex("status"));
        e.locked=c.getInt(c.getColumnIndex("locked"));
        e.arg =c.getString(c.getColumnIndex("arg"));

        return e;
    }
}
