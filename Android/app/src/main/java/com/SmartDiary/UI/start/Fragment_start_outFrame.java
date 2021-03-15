package com.SmartDiary.UI.start;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.SmartDiary.MainActivity;
import com.SmartDiary.R;
import com.SmartDiary.pojo.RecordEntry;
import com.SmartDiary.service.pojoService.CellEntryService;
import com.SmartDiary.service.pojoService.DayEntryService;
import com.SmartDiary.service.pojoService.RecordEntryService;
import com.SmartDiary.service.pojoService.RecordTemplateService;


public class Fragment_start_outFrame extends Fragment implements On_RecordEntry_Edit_Listener {

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
        findView(view);
        bindView();
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
    //添加记录项按钮
    Button btn_start_add;
    //主题Recyclerview的adapter
    Adapter_Start adapter_start;

    private void findView(View view) {
        btn_start_add=view.findViewById(R.id.btn_start_add);
    }

    private void bindView() {
        //这里对于添加按钮绑定的时间是暂时的
        btn_start_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog_start_chooseTemplate dialog_edit_recordEntry=new Dialog_start_chooseTemplate(getContext(), Fragment_start_outFrame.this);
            }
        });
    }

    private void init_ViewPager(View view) {
        recyclerView_start=view.findViewById(R.id.recyclerView_start);
        adapter_start=new Adapter_Start((MainActivity) getActivity());
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this.getContext(), RecyclerView.VERTICAL,false);

        StaggeredGridLayoutManager staggeredGridLayoutManager=
                new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);

        recyclerView_start.setLayoutManager(staggeredGridLayoutManager);
        recyclerView_start.setAdapter(adapter_start);
    }

    /*当新建了一个记录项后就会调用此方法,参数就是创建好的记录项
    * 含有模板id,模板格式,
    * 以及记录项的名称,描述和图标,*/
    @Override
    public void edit_recordEntry_done(RecordEntry newEntry) {
        /*
        * 1.给entry搞个别的id,
        * 2.把entry添加到数据库中,
        * 3.更新*/

        //1:这里简单的用毫秒值当id了
        newEntry.setId(String.valueOf(System.currentTimeMillis()));

        //2:有两个操作
        RecordEntryService.newInstance().add_recordEntry(newEntry);
        CellEntryService.newInstance().add_recordEntry(newEntry.getId());

        //3:应该把三个界面都更新了,这里只更新start界面
        adapter_start.update_entryList();
        adapter_start.notifyDataSetChanged();

    }
}