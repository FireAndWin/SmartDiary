package com.SmartDiary.UI.record;
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

import com.androidkun.xtablayout.XTabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Record_OutFrame#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Record_OutFrame extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_Record_OutFrame() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_m3_Record_OutFrame.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Record_OutFrame newInstance(String param1, String param2) {

        Fragment_Record_OutFrame fragment = new Fragment_Record_OutFrame();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment__record__out_frame, container, false);


        //这一步应该给这两个控件加载数据,但是Manager模块还没有做出来,这里就采用一个测试方法;
        test_loadData();

        init_tabLayout_and_viewPager(view);
        return view;
    }

    //############################1.自定义成员变量#########################
    //---------1.1.和控件相关的-----------
    //lyh:就是记录页面上面那个可以切换记录项的标签栏
    XTabLayout tablayout_m3Record_RecordItemChoose;
    //lyh:标签栏下方实际盛放内容Fragment的ViewPager,会和标签栏联动,下面滑上面也动
    ViewPager viewPager_m3Record_fragmentContainer;
    //lyh:viewPager_m3Record_fragmentContainer的适配器
    Adapter_viewPager_m3Record_fragmentContainer adapter;
    /*
     * lyh:
     * 是viewPager_m3Record_fragmentContainer哪个放实际内容ViewPager的适配器*/
    class Adapter_viewPager_m3Record_fragmentContainer extends FragmentPagerAdapter {
        public Adapter_viewPager_m3Record_fragmentContainer(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return (Fragment) list_contentFragments.get(i);
        }

        @Override
        public int getCount() {
            return list_contentFragments.size();
        }

    }

    //---------1.2.非控件相关的成员-------------
    //lyh:非暂时变量,储存所有内容Fragment的列表
    List<Fragment_Record_RecorditemContent> list_contentFragments = new ArrayList();
    //lyh:暂时使用的,为了方便定义的记录项类
    class Test_RecordItem {
        public String name;
        public String comment;
        public String icon;
        public long id;
    }
    //lyh:也是暂时变量,储存Test_RecordItem的列表,使用于:init_tabLayout_and_viewPager(),test_loadData()
    List<Test_RecordItem> test_list_recordItems;



    //############################2.自定义函数##########################
    /*
     * lyh:
     * 用来初始化两个重要控件:
     * 1.给两个控件成员赋值,不是空指针了;
     * 2.初始化适配器;
     * 3.关联ViewPager及其适配器;
     * 4.关联标签栏和ViewPager
     * 5.(使用了暂时变量test_list_recordItems)给标签栏添加标签
     *
     * 在本类中的onCreateView被调用;
     *
     * param:
     * view就是这个Fragment要加载的View*/
    public void init_tabLayout_and_viewPager(View view){
        //1.从View中初始化控件
        tablayout_m3Record_RecordItemChoose=view.findViewById(R.id.tablayout_m3Record_RecordItemChoose);
        viewPager_m3Record_fragmentContainer=view.findViewById(R.id.viewPager_m3Record_fragmentContainer);

        //2.初始适配器
        adapter = new Adapter_viewPager_m3Record_fragmentContainer(getChildFragmentManager());
        //3.关联ViewPager及其适配器;
        viewPager_m3Record_fragmentContainer.setAdapter(adapter);

        //4.关联标签栏和ViewPager
        tablayout_m3Record_RecordItemChoose.setupWithViewPager(viewPager_m3Record_fragmentContainer);

        //5.给标签栏添加标签
        for(int i=0;i<test_list_recordItems.size();i++){
            XTabLayout.Tab tab=tablayout_m3Record_RecordItemChoose.getTabAt(i);
            tab.setText(test_list_recordItems.get(i).name);
        }

    }

    /*
     * lyh
     * 测试方法:
     * 用来给两个控件加载数据,
     * 这里随便加点数据,实际应该从Manager那里获取数据,
     *这里用来储存记录项的数据结构是Test_RecordItem,是自定义内部类,也是开发本模块暂用的,
     *
     * 本函数的作用是改变list_fragment_m3_Record_RecorditemContents(储存所有内容Fragment的列表)
     * 和Test_list_recordItems(储存所有记录项的列表)
     *
     * 在本类中的onCreateView()被调用一次.*/
    public void test_loadData(){

        list_contentFragments=new ArrayList<>();
        test_list_recordItems=new ArrayList<>();

        String names[]={"日记","学习情况","睡眠情况","体重","记录项"};
        String comments[]={"记录生活精彩瞬间","记录每天学习情况","记录睡觉大概时间","把体重记录一下","备注信息"};

        //这里加载5个
        for( int i=0;i<5;i++){
            Test_RecordItem test_recordItem=new Test_RecordItem();
            test_recordItem.name=names[i];
            test_recordItem.comment=comments[i];

            test_list_recordItems.add(test_recordItem);
            Fragment_Record_RecorditemContent fragment_m3_record_recorditemContent=Fragment_Record_RecorditemContent.newInstance("good","good");
            list_contentFragments.add(fragment_m3_record_recorditemContent);
        }

    }
}