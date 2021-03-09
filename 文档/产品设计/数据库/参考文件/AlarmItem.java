package visualAlarm.src.dataBase;

import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;

import static android.content.ContentValues.TAG;

public class AlarmItem {

    public static final String TAG = "DB_AlarmItem";
    //---------管理相关属性
    public long  id;
    public  String name;

    //设定时的毫秒值的历史列表
    ArrayList<Long> begin_history;
    //是否启用
    public boolean counting;
    //所属标签的id列表
    ArrayList<Long> tag_list;
    //是否删除
    public  boolean deleted;

    //----------时间提醒相关
    //计时模式,0-倒计时,1-设定时间
    int mode;
    //------倒计时模式下
    // 距离目标的毫秒数
    long counting_millis;
    //监听的应用列表
    ArrayList<String> monitor_app;
    //-----设定时间下
    // 目标设定的分钟数
    int fixed_minute;
    //-----每种重复方式
    ArrayList<Integer> weekly_repeat;

    //-----富文本,实际的提醒内容
    String content;

    //-----和提醒时的铃声相关的
    boolean vibrate;
    String alert;

    //-----一起响的闹钟的id列表
    ArrayList<Long> together_list;

    //密码相关
    public int password;
    /*构造方法:
    * 主要有两个工作,
    * 1.给一些必要的值初始化,比如时间,是否删除,是否正在计时;
    * 2.初始化ArrayList,防止空指针*/
    public AlarmItem(){

        //id取当前毫秒值,防止重复
        id=System.currentTimeMillis();
        name="新的闹钟";
        begin_history=new ArrayList<Long>();
        counting=true;
        tag_list= new ArrayList<Long>();
        deleted=false;
        mode=0;
        counting_millis=20*60*1000;
        monitor_app=new ArrayList<String>();
        password=-1;

        //设定时间的预设值设置为建立闹钟的那一刻
        Calendar now=Calendar.getInstance();
        int hour_of_day=now.get(Calendar.HOUR_OF_DAY);
        int minute_of_day=now.get(Calendar.MINUTE);
        fixed_minute=hour_of_day*60+minute_of_day;

        weekly_repeat=new ArrayList<Integer>();
        content=" ";
        vibrate=false;
        alert="!!!!!!!!!!,默认铃声,现在还没有做,待完善";
        together_list=new ArrayList<Long>();

        test();
    }

    //--------测试中给AlarmItem加工一下的方法
    public void test(){
        ArrayList<Long> arr1=new ArrayList<>();
        arr1.add((long) 1111);
        arr1.add((long) 2222);
        arr1.add((long) 3333);

        counting=false;

        tag_list=arr1;
        begin_history=arr1;
        together_list=arr1;

        weekly_repeat.add(1);
        weekly_repeat.add(2);
        weekly_repeat.add(3);
        weekly_repeat.add(5);

        monitor_app.add("app1");
        monitor_app.add("app3");
        monitor_app.add("app10");

        id=id%1000;

        Log.d(TAG, "test: "+"@@@@@@@@@@@@创建好了一个闹钟项,以下为内容:");
        show();
    }
    //--------用来测试的show方法
    public void show(){
        Log.d(TAG, "show: "+"id:"+id+"\n"
//        +"name:"+name+"\n"
//        +"counting:"+counting+"\n"
//        +"deleted:"+deleted+"\n"
//        +"mode:"+mode+"\n"
//        +"counting_millis:"+counting_millis+"\n"
//        +"fixed_minute:"+fixed_minute+"\n"
//        +"content:"+content+"\n"
//        +"vibrate:"+vibrate+"\n"
//        +"alert:"+alert+"\n"
//        +"begin_history"+DataManager.list2str(begin_history)+"\n"
//        +"tag_list"+DataManager.list2str(tag_list)+"\n"
//        +"monitor_app"+DataManager.list2str(monitor_app)+"\n"
//        +"weekly_repeat"+DataManager.list2str(weekly_repeat)+"\n"
//        +"together_list"+DataManager.list2str(together_list)+"\n"
        );
    }
}
