package com.SmartDiary.service.pojoService;

import com.SmartDiary.pojo.RecordEntry;

import java.util.ArrayList;
import java.util.List;

public class RecordEntryService {

    //=================get(读取相关)方法======================
    /*获取所有的记录项对象*/
    public List<RecordEntry> getAll(){
        List<RecordEntry> test_list=new ArrayList<>();
        test_list.add(new RecordEntry("日记","记日记","1"));
        test_list.add(new RecordEntry("锻炼身体","记日记","2"));
        test_list.add(new RecordEntry("体重","记日记","3"));
        test_list.add(new RecordEntry("学习情况","记日记","4"));
        return test_list;
    }
    /*根据id获取RecordEntry对象*/
    public RecordEntry getObject_ById(String id){
        return null;
    }

    //===============set(修改相关)方法===========================
    public void update(String id,RecordEntry newItem){

    }
}
