package com.SmartDiary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.SmartDiary.UI.Test_Fragment;
import com.SmartDiary.UI.check.Fragment_Check_OutFrame;
import com.SmartDiary.UI.record.Fragment_Record_OutFrame;
import com.SmartDiary.UI.record.Fragment_Record_RecorditemContent;
import com.SmartDiary.UI.start.Fragment_start_outFrame;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {



    //================重写方法==================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init_view();
    }


    //================UI相关成员======================
    ViewPager viewPager_main;
    TabLayout tabLayout_main;

    //=================自定义方法============================
    /*初始化UI相关成员
    * */
    public void init_view(){
        viewPager_main=findViewById(R.id.viewPager_main);
        Adapter_viewPager_main adapter_viewPager_main=new Adapter_viewPager_main(getSupportFragmentManager());
        viewPager_main.setAdapter(adapter_viewPager_main);

        //初始化标签
        init_tab();
    }
    /*初始化标签*/
    public void init_tab(){

        tabLayout_main=findViewById(R.id.tabLayout_main);
        tabLayout_main.setupWithViewPager(viewPager_main);
        TabLayout.Tab list_tab=tabLayout_main.getTabAt(0);
        list_tab.setText("列表");
        TabLayout.Tab record_tab=tabLayout_main.getTabAt(1);
        record_tab.setText("记录");
        TabLayout.Tab check_tab=tabLayout_main.getTabAt(2);
        check_tab.setText("查看");
        TabLayout.Tab me_tab=tabLayout_main.getTabAt(3);
        me_tab.setText("我");
    }
    //=================自定义内部类==========================
    class Adapter_viewPager_main extends FragmentPagerAdapter {



        public Adapter_viewPager_main(@NonNull FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i){
                case 0:
                    return Fragment_start_outFrame.newInstance("","");
                case 1:
                    return Fragment_Record_OutFrame.newInstance("","");
                case 2:
                    return Fragment_Check_OutFrame.newInstance("","");
            }



            return Test_Fragment.newInstance("","");
        }

        @Override
        public int getCount() {
            return 4;
        }

    }
}