package com.SmartDiary.UI.record;
import com.SmartDiary.MainActivity;
import com.SmartDiary.R;



import android.content.ClipData;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.SmartDiary.UI.record.StudyRecyclerAdapter;
import com.SmartDiary.pojo.RecordEntry;
import com.SmartDiary.pojo.RecordTemplate;
import com.SmartDiary.service.pojoService.DayEntryService;
import com.SmartDiary.service.pojoService.RecordEntryService;
import com.SmartDiary.service.pojoService.RecordTemplateService;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Record_RecorditemContent#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Record_RecorditemContent extends Fragment {

    //===================构造相关,这么多代码就是为了获取记录项的id,可以直接跳过===========
    private static final String ARG_RecordEntry_id = "RecordEntry_id";
    private String recordEntry_id;
    public Fragment_Record_RecorditemContent() {
    }
    public static Fragment_Record_RecorditemContent newInstance(String recordEntry_id) {
        Fragment_Record_RecorditemContent fragment = new Fragment_Record_RecorditemContent();
        Bundle args = new Bundle();
        args.putString(ARG_RecordEntry_id, recordEntry_id);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            recordEntry_id = getArguments().getString(ARG_RecordEntry_id);
        }
    }



    //=========================重写方法===============================
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment__record__recorditem_content, container, false);
        /*RecyclerView recycler = view.findViewById(R.id.recyclerView_table);
        ;*/

        init_pojoService();
        load_data();
        find_views(view);
        views_load_data();
        init_recordView();
        init_adapters();
        init_tableView();
        bind_event();
        return view;
    }


    //++++++++++++++++++++++++数据成员++++++++++++++++++++++++++++++++++
    //========非UI成员==============
    //---------1.0.pojoService相关成员---
    DayEntryService dayEntryService;
    RecordEntryService recordEntryService;
    RecordTemplateService recordTemplateService;
    //========控件,及其adapter=======
    //就是用来进行 数据/统计结果 换页的ViewPager
    ViewPager viewPager_itemData_multiDisplay;
    //辅助ViewPager进行换页的适配器,是一个内部类,定义在下面
    Adapter_ViewPager adapter_viewPager;
    //通过表格方式(日期-数据)显示数据的那个Recyclerview,定义于布局文件:view_m3_record_table_page中
    RecyclerView recyclerView_table;
    //显示记录项的图片
    ImageView image_itemIcon;
    //显示记录项名称
    TextView text_itemName;
    //显示记录项的备注,描述
    TextView text_itemComment;
    //实际的记录控件
    WebView webView_record_recordData;
    //底部通过表格方显示数据的webview
    WebView webView_record_table;
    //对记录项的信息进行编辑的按钮
    Button btn_itemInfo_edit;
    //在 数据/统计图标 之间进行切换的 单选按钮
    RadioGroup radioGroup_viewChoose;

    //++++++++++++++++++++++++自定义方法++++++++++++++++++++++++++++++++++
    /*这部分一共有5个函数,在onCreateView中依次调用,功能分别是:
     * 1.加载数据(要显示的数据成员,比如List,Map之类的,和控件无关),初始化非UI成员
     *
     * 2.加载控件:通过findViewByID,把控件的加载好,都不是空指针了;(Recyclerview
     * 不在这里初始化,它第5步初始化,因为它已经降级为子控件中的子控件了)
     *
     * 3.向简单控件中加载数据,比如记录项的名称,备注(描述),以及图标
     *
     * 4.处理复杂控件,还有创建各种adapter并初始化,主要是那个Recyclerview,在这里创建Adapter;
     *
     * 5.绑定控件事件,就是那个编辑按钮,还有Recyclerview的点击事件,还有Radio的切换页面,都在这里实现*/
    /*初始化pojoService层的对象*/
    public void init_pojoService() {
        MainActivity mainActivity=(MainActivity) getActivity();
        dayEntryService=mainActivity.dayEntryService;
        recordEntryService=mainActivity.recordEntryService;
        recordTemplateService=mainActivity.recordTemplateService;
    }
    //1.加载数据(要显示的数据成员,比如List,Map之类的,和控件无关);
    public void load_data(){}

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
        RecordEntry entry=recordEntryService.getObject_ById(recordEntry_id);
        text_itemName.setText(entry.getName());
        text_itemComment.setText(entry.getInfo());
    }

    //初始化那个webview的实际记录控件
    public void init_recordView(){
        RecordTemplate template=recordTemplateService.getObject_byID("11");
        String recordView=template.getRecord_view();

        webView_record_recordData.getSettings().setDefaultTextEncodingName("utf-8") ;
        webView_record_recordData.getSettings().setJavaScriptEnabled(true);
        webView_record_recordData.loadDataWithBaseURL(null, recordView, "text/html", "utf-8", null);


    }

    private void init_tableView() {
        webView_record_table=adapter_viewPager.table.findViewById(R.id.webView_record_table);
        Adapter_record_tableView adapter_record_tableView=new Adapter_record_tableView(
                webView_record_table,
                recordEntry_id,
                recordEntryService,
                recordTemplateService
        );
    }


    //4.处理复杂控件,还有创建各种adapter并初始化,主要是那个Recyclerview,在这里创建Adapter;
    public void init_adapters(){
        //这里是创建ViewPager的Adapter;
        adapter_viewPager=new Adapter_ViewPager();
        viewPager_itemData_multiDisplay.setAdapter(adapter_viewPager);

        //从ViewPager的Adapter获取到recyclerView
        recyclerView_table= adapter_viewPager.table.findViewById(R.id.recyclerView_table);
        //这里还是调用tls写的adapter
        StudyRecyclerAdapter.Item[]itemArray=new StudyRecyclerAdapter.Item[20];
        List<StudyRecyclerAdapter.Item> list=new ArrayList<>();
        for(int i=0;i<itemArray.length;++i){
            itemArray[i]=new StudyRecyclerAdapter.Item("2020年2月8日","今天很nice哦！");
            list.add(itemArray[i]);
        }
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this.getContext(),RecyclerView.VERTICAL,false);
        recyclerView_table.setLayoutManager(linearLayoutManager);
        StudyRecyclerAdapter adapter=new StudyRecyclerAdapter(list,this.getContext(),dayEntryService);
        recyclerView_table.setAdapter(adapter);
    }

    //5.绑定控件事件,就是那个编辑按钮,还有Recyclerview的点击事件,还有Radio的切换页面,都在这里实现
    public void bind_event(){

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
            table=LayoutInflater.from((Context)getActivity()).inflate(R.layout.view_record_table_page,null);
            chart=LayoutInflater.from((Context)getActivity()).inflate(R.layout.view_record_chart_page,null);
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