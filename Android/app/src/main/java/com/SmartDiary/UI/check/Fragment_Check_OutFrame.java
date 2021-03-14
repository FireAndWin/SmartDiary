package com.SmartDiary.UI.check;
import com.SmartDiary.R;
import com.SmartDiary.pojo.DayEntry;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Check_OutFrame#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Check_OutFrame extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_Check_OutFrame() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Check_OutFrame.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Check_OutFrame newInstance(String param1, String param2) {
        Fragment_Check_OutFrame fragment = new Fragment_Check_OutFrame();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }



    //==========================================================
    //外层的vewPager
    ViewPager viewPager_check_content;
    //内层的ViewPager
    ViewPager viewPager_checkContent_swipe;
    class Test_Daily_Data{
        String date;
        Map<String,String> daily_data;
    }
    List<Test_Daily_Data> test_data_list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment__check__out_frame, container, false);
        test_init_data();
        init_view(view);
        return view;
    }


    public void init_view(View view){
        viewPager_check_content=view.findViewById(R.id.viewPager_check_content);
        Adapter_Out_ViewPager adapter_viewPager=new Adapter_Out_ViewPager();
        viewPager_check_content.setAdapter(adapter_viewPager);


        viewPager_checkContent_swipe=adapter_viewPager.table.findViewById(R.id.viewPager_checkContent_swipe);
        Adapter_Inner_ViewPager adapter_inner_viewPager=new Adapter_Inner_ViewPager();
        viewPager_checkContent_swipe.setAdapter(adapter_inner_viewPager);
    }

    public void test_init_data(){
        test_data_list=new ArrayList<>();

        Test_Daily_Data day=new Test_Daily_Data();
        day.date="1.1";
        day.daily_data=new HashMap<>();
        day.daily_data.put("A","1");
        day.daily_data.put("b","2");

        Test_Daily_Data day2=new Test_Daily_Data();
        day2.date="1.1";
        day2.daily_data=new HashMap<>();
        day2.daily_data.put("A","1");
        day2.daily_data.put("b","2");

        test_data_list.add(day);
        test_data_list.add(day2);

    }

    //===================================================================

    class Adapter_Inner_ViewPager extends PagerAdapter {

        Map<Integer,View> view_check_content_dailyMap;
        List<DayEntry> dayEntryList;

        //构造函数,主要是初始化这两个View
        public Adapter_Inner_ViewPager(){
            view_check_content_dailyMap=new HashMap<>();
            dayEntryList=new ArrayList<>();

            //这里是为了开发随便加几个数据就完事
            dayEntryList.add(new DayEntry());
            dayEntryList.add(new DayEntry());
        }

        @Override
        public int getCount() {
            return dayEntryList.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view=LayoutInflater.from((Context)getActivity()).inflate(R.layout.view_check_content_daily,null);
            new Adapter_check_dayEntry2webView(view,getContext(),dayEntryList.get(position));
            container.addView(view);
            view_check_content_dailyMap.put(position,view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(view_check_content_dailyMap.get(position));
            view_check_content_dailyMap.remove(position);
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view==object;
        }
    }

    class Adapter_Out_ViewPager extends PagerAdapter {

        //第一页,用表格显示数据
        public View table;
        //第二页,显示图标即统计结果
        public View analysis;

        //构造函数,主要是初始化这两个View
        public Adapter_Out_ViewPager(){
            table=LayoutInflater.from((Context)getActivity()).inflate(R.layout.view_check_table,null);
            analysis=LayoutInflater.from((Context)getActivity()).inflate(R.layout.view_check_analysis,null);
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
                container.addView(analysis);
                return analysis;
            }
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            if (position == 0) {
                container.removeView(table);
            } else {
                container.removeView(analysis);
            }
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view==object;
        }
    }
}