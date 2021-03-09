package visualAlarm.src.dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

//负责外部模块和数据库的交互,提供修改数据的借口
public class DataManager {

    public static final String TAG = "DB_Manager";

    //----要和其他模块交互时使用的数据对象
    ArrayList<AlarmItem> alarmItems;
    ArrayList<TagItem> tagItems;

    //数据库表名
    String alarmItem_db_name="alarmItem.db";
    String alarmItem_table_name="AlarmItem";
    String tagItem_db_name="tagItem.db";
    String tagItem_table_name="TagItem";

    //数据库对象
    SQLiteDatabase alarmItem_table;
    SQLiteDatabase tagItem_table;

    //-----------使用的常量区---------------
    static String separator =";;;";

    Context context;

    public DataManager(Context context){
        this.context=context;

        //初始化Openner
        AlarmItemSQL alarmItemSQL=new AlarmItemSQL(context,alarmItem_db_name,1);
        alarmItem_table= alarmItemSQL.getWritableDatabase();

        fake_init_list();
    }
    //-----------初始化alarmItems和tagItems两个列表对象,不过这里是测试用的
    public void fake_init_list(){
        tagItems=new ArrayList<>();
        alarmItems=new ArrayList<>();
        //这几个Tag和Alarm都是暂时创建的,不是从数据库中取出的

        TagItem study_tag=new TagItem("学习");
        TagItem sleep_tag=new TagItem("睡觉");
        for(int i=0;i<10;i++){
            AlarmItem alarmItem=new AlarmItem();
            alarmItem.name="学习"+i;
            study_tag.contained_list.add(alarmItem.id);
            alarmItems.add(alarmItem);
        }
        for(int i=0;i<5;i++){
            AlarmItem alarmItem=new AlarmItem();
            alarmItem.name="睡觉"+i;
            sleep_tag.contained_list.add(alarmItem.id);
            alarmItems.add(alarmItem);
        }

        tagItems.add(new TagItem("全部"));
        tagItems.add(study_tag);
        tagItems.add(sleep_tag);
        tagItems.add(new TagItem("跑步"));
        tagItems.add(new TagItem("别看手机了"));
        tagItems.add(new TagItem("弹吉他"));
        tagItems.add(new TagItem("画画"));
        tagItems.add(new TagItem("喝水"));
    }
    //========================和其他模块交互时提供的接口==============================
    //+++++++++++++++和闹钟对象相关的+++++++++++++++++++++++
    //**********增加相关
    //-------添加闹钟项
    public AlarmItem add_alarmItem(){
        AlarmItem alarmItem=new AlarmItem();
        alarmItems.add(alarmItem);
        db_add_alarmItem(alarmItem);
        return alarmItem;
    }
    //**********删除相关
    //-------假.删除闹钟项数据
    public void delete_alarmItem(long id){

    }
    //-------真.删除闹钟项数据
    public void real_delete_alarmItem(long id){}
    //-------还原闹钟项数据
    public void restore_alarmItem(long id){

    }
    //**********修改相关
    //-------修改闹钟项数据,主要是要更新数据库
    public void update_alarmItem_in_db(AlarmItem alarmItem){

    }
    //**********查询相关
    //-------根据标签对象获取闹钟项,都是可用的(没有被删除的)
    public ArrayList<AlarmItem> get_alarmItems_by_tagItem(TagItem tagItem){
        ArrayList<AlarmItem> res_arr=new ArrayList<>();
        for(Long alarmItem_id:tagItem.contained_list){
            AlarmItem alarmItem=get_alarmItem_by_id(alarmItem_id);
            if(alarmItem!=null && alarmItem.deleted==false){
                res_arr.add(alarmItem);
            }
        }
        return res_arr;
    }
    //-------根据id获取闹钟项的数据,不论是否删除
    public AlarmItem get_alarmItem_by_id(long id){
        for(AlarmItem alarmItem:alarmItems){
            if(alarmItem.id==id){
                return alarmItem;
            }
        }
        return null;
    }
    //--------获取可用的闹钟,没被删除的
    public ArrayList<AlarmItem> get_alarmItems_available(){return null;}
    //--------获取被删除的闹钟项
    public ArrayList<AlarmItem> get_alarmItems_deleted(){return null;}

    //+++++++++++++++和标签对象相关的+++++++++++++++++++++++
    //-------新建标签对象
    public void add_tagItem(TagItem tagItem){}
    //-------删除标签对象
    public void delete_tagItem(long id){}
    //-------修改标签对象
    public void update_tagItem(){}
    //-------获取所有标签对象
    public ArrayList<TagItem> get_tagItems(){
        return tagItems;
    }
    //-------根据id获取标签对象
    public TagItem get_tagItem_byId(long id){
        for(TagItem tagItem:tagItems){
            if (tagItem._id==id){
                return tagItem;
            }
        }
        return null;
    }
    //=========================对闹钟项的CRUD操作相关==============================

    //************辅助方法**********************
    //-------辅助方法1:将ArrayList类型的成员变量转化为String,方便储存
    public static  <T> String list2str(ArrayList<T> arr){
        StringBuilder res=new StringBuilder();
        for(int i=0;i<arr.size();i++){
            res.append(arr.get(i).toString());
            //这里添加分隔符
            res.append(separator);
        }
        return res.toString();
    }
    //-------辅助方法2:将String转换为ArrayList类型的成员变量,方便解析
    //第二个参数代表目标列表中的类型,可以是0-Integer,1-Long,2-String
    public ArrayList<?> str2list(String src,int mode){
        if (src == null || src .equals("")){
            switch (mode){
                case 0:
                    return new ArrayList<Integer>();
                case 1:
                    return new ArrayList<Long>();
                case 2:
                    return new ArrayList<String>();
            }

        }
        String[] res = src.split(separator);
        if(mode==0){
            ArrayList<Integer> arr = new ArrayList<Integer>();
            for (String re : res) {
                arr.add(Integer.valueOf(re));
            }
            return arr;
        }
        else if(mode==1){
            ArrayList<Long> arr = new ArrayList<Long>();
            for (String re : res) {
                arr.add(Long.valueOf(re));
            }
            return arr;
        }
        else if(mode==2){
            ArrayList<String> arr = new ArrayList<String>();
            Collections.addAll(arr, res);
            return arr;
        }
        return null;
    }
    //-------辅助方法3:将alarmItem的属性转化为ContentValues,为后续操作提供方便
    public ContentValues AlarmItem_2_ContentValues(AlarmItem alarmItem){
        ContentValues contentValues=new ContentValues();

        contentValues.put("_id",alarmItem.id);
        contentValues.put("name",alarmItem.name);
        contentValues.put("begin_history",list2str(alarmItem.begin_history));//---
        contentValues.put("counting",alarmItem.counting);
        contentValues.put("tag_list",list2str(alarmItem.tag_list));//---
        contentValues.put("deleted",alarmItem.deleted);
        contentValues.put("mode",alarmItem.mode);
        contentValues.put("counting_millis",alarmItem.counting_millis);
        contentValues.put("monitor_app",list2str(alarmItem.monitor_app));//---
        contentValues.put("fixed_minute",alarmItem.fixed_minute);
        contentValues.put("weekly_repeat",list2str(alarmItem.weekly_repeat));//---
        contentValues.put("content",alarmItem.content);
        contentValues.put("vibrate",alarmItem.vibrate);
        contentValues.put("alert",alarmItem.alert);
        contentValues.put("together_list",list2str(alarmItem.together_list));//---

        return contentValues;
    }
    //-------辅助方法4:将cursor对象所指的行还原为AlarmItem
    public AlarmItem Cursor_2_AlarmItem(Cursor cursor){
        AlarmItem item=new AlarmItem();

        //遍历Cursor对象,取出数据,放置到AlarmItem中
        item.id=cursor.getLong(cursor.getColumnIndex("_id"));
        item.name=cursor.getString(cursor.getColumnIndex("name"));
        item.begin_history= (ArrayList<Long>) str2list(cursor.getString(cursor.getColumnIndex("_id")),1);
        item.counting=cursor.getInt(cursor.getColumnIndex("counting"))==1;
        item.tag_list=(ArrayList<Long>) str2list(cursor.getString(cursor.getColumnIndex("tag_list")),1);
        item.deleted=cursor.getInt(cursor.getColumnIndex("deleted"))==1;
        item.mode=cursor.getInt(cursor.getColumnIndex("mode"));
        item.counting_millis=cursor.getLong(cursor.getColumnIndex("counting_millis"));
        item.monitor_app=(ArrayList<String>) str2list(cursor.getString(cursor.getColumnIndex("monitor_app")),2);
        item.fixed_minute=cursor.getInt(cursor.getColumnIndex("fixed_minute"));
        item.weekly_repeat=(ArrayList<Integer>) str2list(cursor.getString(cursor.getColumnIndex("weekly_repeat")),0);
        item.content=cursor.getString(cursor.getColumnIndex("content"));
        item.vibrate=cursor.getInt(cursor.getColumnIndex("vibrate"))==1;
        item.alert=cursor.getString(cursor.getColumnIndex("alert"));
        item.together_list=(ArrayList<Long>) str2list(cursor.getString(cursor.getColumnIndex("together_list")),1);

        return item;
    }

    //*******************实际的CRUD函数所在********************
    //-------添加操作
    public void db_add_alarmItem(AlarmItem alarmItem){
        Log.d(TAG, "add_alarmItem: "+"新建闹钟的id为:"+alarmItem.id);

        alarmItem_table.insert(alarmItem_table_name,null,AlarmItem_2_ContentValues(alarmItem));
    }
    //------删除操作
    public void db_delete_alarmItem(long id){
        alarmItem_table.delete(alarmItem_table_name,"_id=?",new String[]{String.valueOf(id)});
    }
    //------更新操作
    public void db_update_alarmItem(AlarmItem alarmItem){
        ContentValues values=AlarmItem_2_ContentValues(alarmItem);
        String id=String.valueOf(alarmItem.id);
        alarmItem_table.update(alarmItem_table_name,values,"_id=?",new String[]{id});
    }
    //------查询操作,只看一个闹钟项
    public AlarmItem db_query_alarmItem(long id){
        Cursor cursor= alarmItem_table.query(alarmItem_table_name,null,"_id=?",new String[]{String.valueOf(id)},null,null,null);
        AlarmItem item=null;
        if (cursor.moveToFirst())
            item=Cursor_2_AlarmItem(cursor);
        cursor.close();
        return item;
    }
    //------查询操作,获取所有闹钟项
    public ArrayList<AlarmItem> db_get_all_alarmItem(){
        Cursor cursor= alarmItem_table.query(alarmItem_table_name,null,null,null,null,null,null);
        ArrayList<AlarmItem> arrayList=new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                arrayList.add(Cursor_2_AlarmItem(cursor));
            }while(cursor.moveToNext());
        }
        cursor.close();
        return arrayList;
    }


    //===========================对分组项的CRUD操作======================
    public ArrayList<TagItem> db_get_all_tagItem(){

        return fake_get_all_tagItem();
    }

    public ArrayList<TagItem> fake_get_all_tagItem(){
        ArrayList<TagItem> arrayList=new ArrayList<>();
        arrayList.add(new TagItem("全部"));
        arrayList.add(new TagItem("学习"));
        arrayList.add(new TagItem("睡觉"));
        arrayList.add(new TagItem("跑步"));
        arrayList.add(new TagItem("别看手机了"));
        arrayList.add(new TagItem("弹吉他"));
        arrayList.add(new TagItem("画画"));
        arrayList.add(new TagItem("喝水"));
        return arrayList;
    }
}
