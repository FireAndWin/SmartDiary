package com.SmartDiary.UI.record;
import com.SmartDiary.MainActivity;
import com.SmartDiary.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Record_OutFrame#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Record_OutFrame extends Fragment {


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
    // ---------1.1.和控件相关的-----------
    //lyh:就是记录页面上面那个可以切换记录项的标签栏
    XTabLayout tablayout_m3Record_RecordItemChoose;
    //lyh:标签栏下方实际盛放内容Fragment的ViewPager,会和标签栏联动,下面滑上面也动
    ViewPager viewPager_m3Record_fragmentContainer;
    //lyh:viewPager_m3Record_fragmentContainer的适配器
    Adapter_viewPager_m3Record_fragmentContainer adapter;



    //############################2.自定义函数##########################

    /*初始化pojoService层的对象*/
    public void init_pojoService() {
        MainActivity mainActivity=(MainActivity) getActivity();
        dayEntryService=mainActivity.dayEntryService;
        recordEntryService=mainActivity.recordEntryService;
        recordTemplateService=mainActivity.recordTemplateService;
    }

    private void init_viewPager(View view) {
        viewPager_m3Record_fragmentContainer=view.findViewById(R.id.viewPager_m3Record_fragmentContainer);
        //2.初始适配器
        adapter = new Adapter_viewPager_m3Record_fragmentContainer(getChildFragmentManager());
        //3.关联ViewPager及其适配器;
        viewPager_m3Record_fragmentContainer.setAdapter(adapter);

    }

    private void init_tabLayout(View view) {
        //从View中初始化控件
        tablayout_m3Record_RecordItemChoose=view.findViewById(R.id.tablayout_m3Record_RecordItemChoose);
        //关联标签栏和ViewPager
        tablayout_m3Record_RecordItemChoose.setupWithViewPager(viewPager_m3Record_fragmentContainer);
        List<RecordEntry> recordEntryList=recordEntryService.getAll();
        for(int i=0;i<recordEntryList.size();i++){
            XTabLayout.Tab tab=tablayout_m3Record_RecordItemChoose.getTabAt(i);
            tab.setText(recordEntryList.get(i).getName());
        }
    }


    //===================================自定义内部类===================================
    /*
     * lyh:
     * 是viewPager_m3Record_fragmentContainer哪个放实际内容ViewPager的适配器*/
    class Adapter_viewPager_m3Record_fragmentContainer extends FragmentPagerAdapter {
        public Adapter_viewPager_m3Record_fragmentContainer(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            String id=recordEntryService.getAll().get(i).getId();
            Fragment_Record_RecorditemContent record_recorditemContent=Fragment_Record_RecorditemContent.newInstance(id);
            return record_recorditemContent;
        }

        @Override
        public int getCount() {
            return recordEntryService.getAll().size();
        }

    }
}