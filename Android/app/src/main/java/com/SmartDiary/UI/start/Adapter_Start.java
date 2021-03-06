package com.SmartDiary.UI.start;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.SmartDiary.MainActivity;
import com.SmartDiary.R;
import com.SmartDiary.pojo.RecordEntry;
import com.SmartDiary.service.pojoService.DayEntryService;
import com.SmartDiary.service.pojoService.RecordEntryService;
import com.SmartDiary.service.pojoService.RecordTemplateService;

import java.util.List;

public class Adapter_Start extends RecyclerView.Adapter<Adapter_Start.ViewHolder>{

    //储存着所有具体记录项的列表
    List<RecordEntry> entryList;
    //pojoService相关对象
    DayEntryService dayEntryService;
    RecordEntryService recordEntryService;
    RecordTemplateService recordTemplateService;
    //主活动的应用
    MainActivity mainActivity;

    //用来初始化pojoService对象和entryList
    public Adapter_Start(MainActivity mainActivity) {
        this.mainActivity=mainActivity;
        init_pojoService();
        update_entryList();
    }

    private void init_pojoService() {
        dayEntryService=mainActivity.dayEntryService;
        recordEntryService=mainActivity.recordEntryService;
        recordTemplateService=mainActivity.recordTemplateService;
    }

    private void update_entryList() {
        entryList=recordEntryService.getAll();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_start_vertical,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RecordEntry entry=entryList.get(position);
        holder.initFromEntry(entry);

    }

    @Override
    public int getItemCount() {
        return entryList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView_startItem_icon;
        TextView textView_startItem_name;
        View view_startItem_recent;
        CheckBox checkbox_startItem_done;
        TextView textView_startItem_latest;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView_startItem_icon=itemView.findViewById(R.id.imageView_startItem_icon);
            textView_startItem_name=itemView.findViewById(R.id.textView_startItem_name);
            view_startItem_recent=itemView.findViewById(R.id.view_startItem_recent);
            checkbox_startItem_done=itemView.findViewById(R.id.checkbox_startItem_done);
            textView_startItem_latest=itemView.findViewById(R.id.textView_startItem_latest);
        }

        //根据RecordEntry初始化控件
        public void initFromEntry(RecordEntry entry){
            textView_startItem_name.setText(entry.getName());
        }
    }

}
