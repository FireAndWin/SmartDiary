package com.SmartDiary.service.pojoService;

import android.os.Bundle;

import com.SmartDiary.pojo.RecordEntry;

import java.util.ArrayList;
import java.util.List;

public class RecordEntryService {

    private static RecordEntryService singleRecordEntryService;
    public static  RecordEntryService newInstance() {
        if(singleRecordEntryService==null){
            singleRecordEntryService=new RecordEntryService();
        }
        return singleRecordEntryService;
    }

    //测试时使用的只存在于内存中的数据
    List<RecordEntry> test_list;
    List<RecordEntry> recordEntryList;
    public RecordEntryService() {
        test_list=new ArrayList<>();
        test_list.add(new RecordEntry("熬夜情况","选择模板的测试","21","11"));
        test_list.add(new RecordEntry("日记","文本模板的测试项","22","12"));
        test_list.add(new RecordEntry("体重","数量模板的记录项","23","13"));

//        test_list.add(new RecordEntry("锻炼身体","年轻人要多锻炼身体","2"));
//        test_list.add(new RecordEntry("体重","要好好吃饭","3"));
//        test_list.add(new RecordEntry("学习情况","记录学习时间内容分布","4"));
//        test_list.add(new RecordEntry("记账","花了多少钱,花在哪里","5"));
//        test_list.add(new RecordEntry("娱乐app使用时长","不要老是玩手机","6"));
//        test_list.add(new RecordEntry("待办事项","TODO list","7"));
//        test_list.add(new RecordEntry("痘痘情况","脸上痘痘的情况","8"));
//        test_list.add(new RecordEntry("雏雁计划日志","进度怎么样","9"));
//        test_list.add(new RecordEntry("心情","心情怎么样","10"));


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

    //===============set(修改相关)方法===========================
    public void update(String id,RecordEntry newItem){

    }
}
