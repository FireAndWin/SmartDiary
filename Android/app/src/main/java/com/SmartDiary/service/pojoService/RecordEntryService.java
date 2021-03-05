package com.SmartDiary.service.pojoService;

import com.SmartDiary.pojo.RecordEntry;

import java.util.ArrayList;
import java.util.List;

public class RecordEntryService {

    //测试时使用的只存在于内存中的数据
    List<RecordEntry> test_list;

    public RecordEntryService() {
        test_list=new ArrayList<>();
        test_list.add(new RecordEntry("日记","记日记","1"));
        test_list.add(new RecordEntry("锻炼身体","记日记","2"));
        test_list.add(new RecordEntry("体重","记日记","3"));
        test_list.add(new RecordEntry("学习情况","记日记","4"));
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
