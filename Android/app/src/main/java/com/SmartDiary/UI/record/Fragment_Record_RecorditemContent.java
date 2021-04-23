package com.SmartDiary.UI.record;
import com.SmartDiary.R;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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


    //核心成员就是viewHolder
    ViewHolder_Record_RecorditemContent viewHolder;
    View view;

    //=========================重写方法===============================
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment__record__recorditem_content, container, false);
        /*RecyclerView recycler = view.findViewById(R.id.recyclerView_table);
        ;*/
        this.view=view;
        setViewHolder();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        viewHolder.onDestroy();
    }

    public void setViewHolder() {
        //页面对view的处理都交给ViewHolder_Record_RecorditemContent了.
        new ViewHolder_Record_RecorditemContent(getContext(),view,recordEntry_id);
    }

}