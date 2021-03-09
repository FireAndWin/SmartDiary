package com.SmartDiary.service.pojoService;

public class DayEntryService {

    private static DayEntryService singleDayEntryService;
    public static  DayEntryService newInstance() {
        if(singleDayEntryService==null){
            singleDayEntryService=new DayEntryService();
        }
        return singleDayEntryService;
    }
}
