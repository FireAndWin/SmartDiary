package com.SmartDiary.UI.record;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.SmartDiary.MainActivity;
import com.SmartDiary.R;
import com.SmartDiary.UI.start.Dialog_start_editRecordEntry;
import com.SmartDiary.UI.start.On_RecordEntry_Edit_Listener;
import com.SmartDiary.Utils.TimeUtils;
import com.SmartDiary.pojo.RecordEntry;
import com.SmartDiary.service.pojoService.DayEntryService;
import com.SmartDiary.service.pojoService.RecordEntryService;
import com.SmartDiary.service.pojoService.RecordTemplateService;

/*
* 这个类就是为了给控件添加数据和事件的,
* 对应于记录页面的具体内容fragment,
* 这样做是为了把页面具体加工内容从Fragment中独立出去,
* 就可以把本页面中的内容显示在一个对话框中,
* 为了start页面有些不是每日记录的,
* 所以不会加入到记录界面,
* 解决方法就是用对话框.*/
public class ViewHolder_Record_RecorditemContent implements On_RecordEntry_Edit_Listener {
    View view;
    public String recordEntry_id;
    Context context;

    public ViewHolder_Record_RecorditemContent(
            Context context,
            View view,
            String recordEntry_id) {
        this.context=context;
        this.view = view;
        this.recordEntry_id = recordEntry_id;

        init_pojoService();
        find_views(view);
        views_load_data();
        init_recordView();
        init_adapters();
        init_tableView();
        bind_event();
    }


    //++++++++++++++++++++++++数据成员++++++++++++++++++++++++++++++++++
    //========非UI成员==============
    //---------1.0.pojoService相关成员---
    DayEntryService dayEntryService;
    RecordEntryService recordEntryService;
    RecordTemplateService recordTemplateService;
    //---------webView及其相关Adapter
    //实际的记录控件
    WebView webView_record_recordData;
    //记录页面的adapter:
    Adapter4webView_record_recordView adapter_recordView;
    //底部通过表格方显示数据的webview
    WebView webView_record_table;
    //========控件,及其adapter=======
    //就是用来进行 数据/统计结果 换页的ViewPager
    ViewPager viewPager_itemData_multiDisplay;
    //辅助ViewPager进行换页的适配器,是一个内部类,定义在下面
    ViewHolder_Record_RecorditemContent.Adapter_ViewPager adapter_viewPager;
    //通过表格方式(日期-数据)显示数据的那个Recyclerview,定义于布局文件:view_m3_record_table_page中
    RecyclerView recyclerView_table;
    //显示记录项的图片
    ImageView image_itemIcon;
    //显示记录项名称
    TextView text_itemName;
    //显示记录项的备注,描述
    TextView text_itemComment;
    //对记录项的信息进行编辑的按钮
    Button btn_itemInfo_edit;
    //在 数据/统计图标 之间进行切换的 单选按钮
    RadioGroup radioGroup_viewChoose;

    //++++++++++++++++++++++++自定义方法++++++++++++++++++++++++++++++++++
    /*初始化pojoService层的对象*/
    public void init_pojoService() {
        recordEntryService=RecordEntryService.newInstance();
    }

    //2.加载控件:通过findViewByID,把控件的加载好,都不是空指针了;(Recyclerview不在这里初始化,它第5步初始化,因为它已经降级为子控件中的子控件了)
    public void find_views(View view){
        viewPager_itemData_multiDisplay=view.findViewById(R.id.viewPager_itemData_multiDisplay);
        image_itemIcon=view.findViewById(R.id.image_itemIcon);
        text_itemName=view.findViewById(R.id.text_itemName);
        text_itemName.setText(recordEntry_id);
        text_itemComment=view.findViewById(R.id.text_itemComment);
        btn_itemInfo_edit=view.findViewById(R.id.btn_itemInfo_edit);
        webView_record_recordData=view.findViewById(R.id.webView_record_recordData);
        radioGroup_viewChoose=view.findViewById(R.id.radioGroup_viewChoose);
    }

    //3.向简单控件中加载数据,比如记录项的名称,备注(描述),以及图标
    public void views_load_data(){
        RecordEntry entry=recordEntryService.get_recordEntry_byId(recordEntry_id);
        text_itemName.setText(entry.getName());
        text_itemComment.setText(entry.getInfo());
    }

    //初始化那个webview的实际记录控件
    public void init_recordView(){
        adapter_recordView=new Adapter4webView_record_recordView(webView_record_recordData,recordEntry_id, TimeUtils.get_deltaTime_long(0));
        //当webView的焦点改变时,就调用Adapter的实际记录方法
        webView_record_recordData.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                adapter_recordView.do_record_dataBase();
            }
        });

    }

    //当编辑按钮点击后出现对话框,对话框编辑完以后调用本方法
    @Override
    public void edit_recordEntry_done(RecordEntry newEntry) {
        /*
        * 1.第一步,储存到数据库中
        * 2.然后更新本页面*/

        //1.到数据库中更新本页面
        RecordEntryService.newInstance().update_recordEntry(newEntry);

        //2.这里更新本页面的方法,就是调用在构造的时候调用的相关加载数据的方法.
        views_load_data();
        init_recordView();
        init_tableView();

    }

    //初始化底下那个显示数据的webview
    private void init_tableView() {
        webView_record_table=adapter_viewPager.table.findViewById(R.id.webView_record_table);
        Adapter4webView_record_table adapter_4webViewRecordtable =new Adapter4webView_record_table(
                context,
                webView_record_table,
                recordEntry_id,
                recordEntryService,
                recordTemplateService
        );
    }

    //4.处理复杂控件,还有创建各种adapter并初始化,主要是那个Recyclerview,在这里创建Adapter;
    public void init_adapters(){
        //这里是创建ViewPager的Adapter;
        adapter_viewPager=new ViewHolder_Record_RecorditemContent.Adapter_ViewPager();
        viewPager_itemData_multiDisplay.setAdapter(adapter_viewPager);
    }

    //5.绑定控件事件,就是那个编辑按钮,还有Recyclerview的点击事件,还有Radio的切换页面,都在这里实现
    public void bind_event(){

        //当点击编辑按钮后,出现编辑记录项对话框
        btn_itemInfo_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Dialog_start_editRecordEntry(
                        context,
                        RecordEntryService.newInstance().get_recordEntry_byId(recordEntry_id),
                        ViewHolder_Record_RecorditemContent.this
                        );
            }
        });

        //1.给viewPager_itemData_multiDisplay绑定当页面滑动的时候对应的RadioButton被选中
        viewPager_itemData_multiDisplay.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            //在这个函数里面实现了滑动页面对应的RadioButton被选中，但是这里不能实现点击RadioButton对应View切换，这在
            //方法    里面实现
            public void onPageSelected(int position) {
                //position=0代表recyclerView那一页
                if(position==0){
                    RadioButton rb1 =radioGroup_viewChoose.findViewById(R.id.radioBtn_table);
                    rb1.setChecked(true);
                }
                //否则就是chart那一页
                else{
                    RadioButton rb2 =radioGroup_viewChoose.findViewById(R.id.radioBtn_chart);
                    rb2.setChecked(true);
                }
                return;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //2.给RadioGroup绑定点击单选按钮后切换对应的View
        radioGroup_viewChoose.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb1=radioGroup_viewChoose.findViewById(R.id.radioBtn_table);
//                RadioButton rb2=radioGroup_viewChoose.findViewById(R.id.radioBtn_chart);
                if(checkedId==rb1.getId()){
                    viewPager_itemData_multiDisplay.setCurrentItem(0);
                }
                else{
                    viewPager_itemData_multiDisplay.setCurrentItem(1);
                }
            }
        });

        //3.默认设置viewPager_itemData_multiDisplay是在table页，并且默认是"数据"RadioButton被选中
        viewPager_itemData_multiDisplay.setCurrentItem(0);
    }
    //++++++++++++++++++++++内部类,主要是各种Adapter+++++++++++++++++++++
    class Adapter_ViewPager extends PagerAdapter {

        //第一页,用表格显示数据
        public View table;
        //第二页,显示图标即统计结果
        public View chart;

        //构造函数,主要是初始化这两个View
        public Adapter_ViewPager(){
            table= LayoutInflater.from(context).inflate(R.layout.view_record_table_page,null);
            chart=LayoutInflater.from(context).inflate(R.layout.view_record_chart_page,null);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            if (position == 0) {
                container.addView(table);
                return table;
            } else {
                container.addView(chart);
                return chart;
            }
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            if (position == 0) {
                container.removeView(table);
            } else {
                container.removeView(chart);
            }
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view==object;
        }
    }
}
