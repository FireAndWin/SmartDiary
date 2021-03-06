package com.SmartDiary.UI.start;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.SmartDiary.MainActivity;
import com.SmartDiary.R;
import com.SmartDiary.service.pojoService.DayEntryService;
import com.SmartDiary.service.pojoService.RecordEntryService;
import com.SmartDiary.service.pojoService.RecordTemplateService;


public class Fragment_start_outFrame extends Fragment {

    public Fragment_start_outFrame() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //==========================重写方法==================================
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_start_out_frame, container, false);
        init_ViewPager(view);
        return view;
    }




    //========================自定义成员====================
    //---------1.0.pojoService相关成员---
    DayEntryService dayEntryService;
    RecordEntryService recordEntryService;
    RecordTemplateService recordTemplateService;
    //---------1.1.UI相关成员----------------
    RecyclerView recyclerView_start;

    private void init_ViewPager(View view) {
        recyclerView_start=view.findViewById(R.id.recyclerView_start);
        Adapter_Start adapter_start=new Adapter_Start((MainActivity) getActivity());
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this.getContext(), RecyclerView.VERTICAL,false);

        StaggeredGridLayoutManager staggeredGridLayoutManager=
                new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);

        recyclerView_start.setLayoutManager(staggeredGridLayoutManager);
        recyclerView_start.setAdapter(adapter_start);
    }

}