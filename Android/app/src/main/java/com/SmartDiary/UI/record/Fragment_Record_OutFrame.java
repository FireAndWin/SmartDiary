package com.SmartDiary.UI.record;
import com.SmartDiary.MainActivity;
import com.SmartDiary.R;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.SmartDiary.pojo.RecordEntry;
import com.SmartDiary.service.pojoService.DayEntryService;
import com.SmartDiary.service.pojoService.RecordEntryService;
import com.SmartDiary.service.pojoService.RecordTemplateService;
import com.androidkun.xtablayout.XTabLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Record_OutFrame#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Record_OutFrame extends Fragment implements TabLayout.OnTabSelectedListener {


    public Fragment_Record_OutFrame() {
    }

    public static Fragment_Record_OutFrame newInstance() {
        Fragment_Record_OutFrame fragment = new Fragment_Record_OutFrame();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment__record__out_frame, container, false);


        this.view=view;
        //初始化pojoService层的相关对象
        init_pojoService();
        //初始化下方的viewPager
        init_viewPager(view);
        //初始化上方的tablayout
        init_tabLayout(view);

        return view;
    }



    //############################1.自定义成员变量#########################
    //---------1.0.pojoService相关成员---
    DayEntryService dayEntryService;
    RecordEntryService recordEntryService;
    RecordTemplateService recordTemplateService;
    List<RecordEntry> entryList;
    // ---------1.1.和控件相关的-----------
    View view;
    //lyh:就是记录页面上面那个可以切换记录项的标签栏
    TabLayout tablayout_m3Record_RecordItemChoose;
    //lyh:标签栏下方实际盛放内容Fragment的ViewPager,会和标签栏联动,下面滑上面也动
    ViewPager viewPager_m3Record_fragmentContainer;
    //lyh:viewPager_m3Record_fragmentContainer的适配器
    Adapter_viewPager_m3Record_fragmentContainer adapter;



    //############################2.自定义函数##########################
    /*当新建,删除记录项时调用该方法*/
    public void update_view(){
        entryList=recordEntryService.get_recordEntryList_byStatus(1);

        int selected=tablayout_m3Record_RecordItemChoose.getSelectedTabPosition();
        tablayout_m3Record_RecordItemChoose.setupWithViewPager(null);

//        viewPager_m3Record_fragmentContainer.setAdapter(null);
        adapter.using_recordEntryList=entryList;
        adapter.notifyDataSetChanged();
        int a =adapter.getCount();
//        adapter=new Adapter_viewPager_m3Record_fragmentContainer(getChildFragmentManager());
//        viewPager_m3Record_fragmentContainer.setAdapter(adapter);

        tablayout_m3Record_RecordItemChoose.setupWithViewPager(viewPager_m3Record_fragmentContainer);
        List<RecordEntry> recordEntryList=entryList;
        for(int i=0;i<recordEntryList.size();i++){
            TabLayout.Tab tab=tablayout_m3Record_RecordItemChoose.getTabAt(i);
            tab.setCustomView(R.layout.view_record_tab);
            TextView textView= tab.getCustomView().findViewById(R.id.tab_record_name);
            textView.setText(recordEntryList.get(i).getName());
            if (i==selected)
                onTabSelected(tab);
            if(recordEntryList.size()==1)
                onTabSelected(tab);
        }


    }


    /*初始化pojoService层的对象*/
    public void init_pojoService() {
        MainActivity mainActivity=(MainActivity) getActivity();
        dayEntryService=mainActivity.dayEntryService;
        recordEntryService=RecordEntryService.newInstance();
        recordTemplateService=mainActivity.recordTemplateService;
        entryList=recordEntryService.get_recordEntryList_byStatus(1);
    }

    private void init_viewPager(View view) {
        viewPager_m3Record_fragmentContainer=view.findViewById(R.id.viewPager_m3Record_fragmentContainer);
        //2.初始适配器
        adapter = new Adapter_viewPager_m3Record_fragmentContainer(getChildFragmentManager());
        //3.关联ViewPager及其适配器;
        viewPager_m3Record_fragmentContainer.setAdapter(adapter);

        viewPager_m3Record_fragmentContainer.setOffscreenPageLimit(2);
    }

    private void init_tabLayout(View view) {
        //从View中初始化控件
        tablayout_m3Record_RecordItemChoose=view.findViewById(R.id.tablayout_m3Record_RecordItemChoose);
        //关联标签栏和ViewPager
        tablayout_m3Record_RecordItemChoose.setupWithViewPager(viewPager_m3Record_fragmentContainer);
        //List<RecordEntry> recordEntryList=recordEntryService.get_recordEntryList_byStatus(1);
        List<RecordEntry> recordEntryList=entryList;
        for(int i=0;i<recordEntryList.size();i++){
            TabLayout.Tab tab=tablayout_m3Record_RecordItemChoose.getTabAt(i);
            tab.setCustomView(R.layout.view_record_tab);
            TextView textView= tab.getCustomView().findViewById(R.id.tab_record_name);
            textView.setText(recordEntryList.get(i).getName());
            if(i==0)
                onTabSelected(tab);
            //tab.setText(recordEntryList.get(i).getName());
        }
        tablayout_m3Record_RecordItemChoose.addOnTabSelectedListener(this);
    }


    //===================================自定义内部类===================================
    /*
     * lyh:
     * 是viewPager_m3Record_fragmentContainer哪个放实际内容ViewPager的适配器*/
    class Adapter_viewPager_m3Record_fragmentContainer extends FragmentPagerAdapter {

        private List<Fragment_Record_RecorditemContent> mFragments;
        List<RecordEntry> using_recordEntryList;
        private List<String> tags;
        FragmentManager fragmentManager;

        public Adapter_viewPager_m3Record_fragmentContainer(FragmentManager fm) {
            super(fm);
            fragmentManager=fm;
            //using_recordEntryList=recordEntryService.get_recordEntryList_byStatus(1);
            using_recordEntryList=entryList;
            this.tags = new ArrayList<>();
            mFragments=new ArrayList<>();
        }


        @Override
        public Fragment getItem(int i) {
            String id=using_recordEntryList.get(i).getId();
            Fragment_Record_RecorditemContent record_recorditemContent=Fragment_Record_RecorditemContent.newInstance(id);
            mFragments.add(record_recorditemContent);
            return record_recorditemContent;

        }

        @Override
        public int getCount() {
            return using_recordEntryList.size();
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            super.destroyItem(container, position, object);
        }
    }


    //==============================上方那个TabLayout的事件实现
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
//        TextView old=tab.getCustomView().findViewById(R.id.tab_record_name);
//        String name= (String) old.getText();
//        tab.setCustomView(R.layout.view_record_tab_selected);
//        View view=tab.getCustomView();
//        view.setBackground(R.drawable.);
//        TextView textView= tab.getCustomView().findViewById(R.id.tab_record_name_selected);
//        textView.setText(name);
        View view=tab.getCustomView();
        if(view==null)
            return;
        TextView textView=view.findViewById(R.id.tab_record_name);
        textView.setBackgroundResource(R.drawable.tab_selected);
        textView.setTextColor(0xffFFFFFF);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        View view=tab.getCustomView();
        if(view==null)
            return;
        TextView textView=view.findViewById(R.id.tab_record_name);
        textView.setBackgroundResource(R.drawable.tab_normal);
        textView.setTextColor(0xff808080);
//        TextView old=tab.getCustomView().findViewById(R.id.tab_record_name_selected);
//        if(old!=null){
//            String name= (String) old.getText();
//            tab.setCustomView(R.layout.view_record_tab);
//            TextView textView= tab.getCustomView().findViewById(R.id.tab_record_name);
//            textView.setText(name);
//        }
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}